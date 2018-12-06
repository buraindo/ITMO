package ru.itmo.wm4.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wm4.form.NoticeForm;

@Component
public class NoticeFormSubmitValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NoticeForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            NoticeForm noticeForm = (NoticeForm) target;
            String text = noticeForm.getText();
            if (text.isEmpty()) {
                errors.rejectValue("text", "text.must.not.be.empty", "text must not be empty");
            } else if (text.length() < 10) {
                errors.rejectValue("text", "text.must.be.longer", "text must be longer than 10 characters");
            }
        }
    }
}
