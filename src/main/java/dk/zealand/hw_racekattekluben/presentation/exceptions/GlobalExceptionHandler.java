package dk.zealand.hw_racekattekluben.presentation.exceptions;

import dk.zealand.hw_racekattekluben.domain.exceptions.*;
import dk.zealand.hw_racekattekluben.infrastructure.exceptions.DatabaseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DatabaseException.class)
    public String handleDatabaseException(DatabaseException ex, Model model) {
        model.addAttribute("fejl", List.of(ex.getMessage()));
        return "error";
    }

    @ExceptionHandler(CatNotFoundException.class)
    public String handleCatNotFoundException(CatNotFoundException ex, Model model) {
        model.addAttribute("fejl", List.of(ex.getMessage()));
        return "error";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException(MemberNotFoundException ex, Model model) {
        model.addAttribute("fejl", List.of(ex.getMessage()));
        return "error";
    }

    @ExceptionHandler(MemberAlreadyAdminException.class)
    public String handleMemberAlreadyAdminException(MemberAlreadyAdminException ex, Model model) {
        model.addAttribute("fejl", List.of(ex.getMessage()));
        return "error";
    }

    @ExceptionHandler(MemberAlreadyBreederException.class)
    public String handleMemberAlreadyBreederException(MemberAlreadyBreederException ex, Model model) {
        model.addAttribute("fejl", List.of(ex.getMessage()));
        return "error";
    }

    @ExceptionHandler(CatAlreadyDeadException.class)
    public String handleCatAlreadyDeadException(CatAlreadyDeadException ex, Model model) {
        model.addAttribute("fejl", List.of(ex.getMessage()));
        return "error";
    }

    @ExceptionHandler(InvalidDeathdateException.class)
    public String handleInvalidDeathdateException(InvalidDeathdateException ex, Model model) {
        model.addAttribute("fejl", List.of(ex.getMessage()));
        return "error";
    }

    @ExceptionHandler(ExhibitionNotFoundException.class)
    public String handleExhibitionNotFoundException(ExhibitionNotFoundException ex, Model model) {
        model.addAttribute("fejl", List.of(ex.getMessage()));
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("fejl", List.of("Noget gik galt: " + ex.getMessage()));
        return "error";
    }
}
