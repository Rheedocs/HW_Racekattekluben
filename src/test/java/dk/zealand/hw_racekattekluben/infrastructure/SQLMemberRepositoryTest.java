package dk.zealand.hw_racekattekluben.infrastructure;

import dk.zealand.hw_racekattekluben.domain.Member;
import dk.zealand.hw_racekattekluben.domain.enums.Role;
import dk.zealand.hw_racekattekluben.infrastructure.mappers.MemberRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import({SQLMemberRepository.class, MemberRowMapper.class})
class SQLMemberRepositoryTest {

    @Autowired
    private SQLMemberRepository repository;

    @Test
    void findAll_returnsAllMembers() {
        List<Member> members = repository.findAll();
        assertEquals(4, members.size());
    }

    @Test
    void findById_existingId_returnsMember() {
        Member member = repository.findById(1);
        assertNotNull(member);
        assertEquals("Anders Nielsen", member.getName());
    }

    @Test
    void findById_nonExistingId_returnsNull() {
        Member member = repository.findById(999);
        assertNull(member);
    }

    @Test
    void findByEmail_existingEmail_returnsMember() {
        Member member = repository.findByEmail("birgitte@mail.dk");
        assertNotNull(member);
        assertEquals("Birgitte Hansen", member.getName());
    }

    @Test
    void findByEmail_nonExistingEmail_returnsNull() {
        Member member = repository.findByEmail("ikkeeksisterende@mail.dk");
        assertNull(member);
    }

    @Test
    void save_newMember_canBeFoundAfterwards() {
        Member member = new Member("Test Person", "test@mail.dk", "password", Role.USER, false);
        repository.save(member);

        Member found = repository.findByEmail("test@mail.dk");
        assertNotNull(found);
        assertEquals("Test Person", found.getName());
    }

    @Test
    void update_existingMember_updatesCorrectly() {
        Member member = repository.findById(1);
        member.setName("Opdateret Navn");
        repository.update(member);

        Member updated = repository.findById(1);
        assertEquals("Opdateret Navn", updated.getName());
    }

    @Test
    void delete_existingMember_cannotBeFoundAfterwards() {
        repository.delete(1);
        Member deleted = repository.findById(1);
        assertNull(deleted);
    }
}