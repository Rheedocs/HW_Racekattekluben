package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.IMemberRepository;
import dk.zealand.hw_racekattekluben.domain.Member;
import dk.zealand.hw_racekattekluben.domain.enums.Role;

import dk.zealand.hw_racekattekluben.domain.exceptions.MemberNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private IMemberRepository memberRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("Anders Nielsen", "anders@mail.dk", "password123",
                Role.USER, false);
    }

    @Test
    void getMemberById_ifInvalidID_throwMemberNotFoundException() {
        when(memberRepository.findById(99)).thenReturn(null);
        assertThrows(MemberNotFoundException.class, () -> memberService.getById(99));
    }

    @Test
    void getMemberById_ifValidID_returnsMember() {
        when(memberRepository.findById(1)).thenReturn(member);
        Member result = memberService.getById(1);
        assertEquals(member, result);
    }

    @Test
    void getMemberByEmail_ifInvalidEmail_throwMemberNotFoundException() {
        when(memberRepository.findByEmail("anders@mail.dk")).thenReturn(null);
        assertThrows(MemberNotFoundException.class, () -> memberService.getByEmail("anders@mail.dk"));
    }
    @Test
    void getMemberByEmail_ifValidEmail_returnsMember() {
        when(memberRepository.findByEmail("anders@mail.dk")).thenReturn(member);
        Member result = memberService.getByEmail("anders@mail.dk");
        assertEquals(member, result);
    }

    @Test
    void getAll_returnsAllMembers() {
        when(memberRepository.findAll()).thenReturn(List.of(member));
        List<Member> result = memberService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void create_withValidData_savesMember() {
        when(passwordEncoder.encode("password123")).thenReturn("encoded");
        memberService.create("Anders Nielsen", "anders@mail.dk", "password123", false);
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    void update_withValidMember_savesMember() {
        member.setId(1);
        when(memberRepository.findById(1)).thenReturn(member);
        memberService.update(member, false);
        verify(memberRepository).update(any(Member.class));
    }

    @Test
    void delete_withValidMember_deletesMember() {
        member.setId(1);
        when(memberRepository.findById(1)).thenReturn(member);
        memberService.delete(1);
        verify(memberRepository).delete(1);
    }
}