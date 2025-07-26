package com.github.fabriciolfj.simulation.entrypoint.common;

import com.github.fabriciolfj.simulation.exceptions.clazz.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequiredArgsConstructor
@Component
public class ValidationHandler {

    private final Validator validator;
    private final MessageSource messageSource;

    public <T> Mono<T> validate(T object) {
        return Mono.fromCallable(() -> {
            Set<ConstraintViolation<T>> violations = validator.validate(object);

            if (!violations.isEmpty()) {
                String errorMessages = violations.stream()
                        .map(violation -> {
                            String message = violation.getMessage();
                            if (message.startsWith("{") && message.endsWith("}")) {
                                String key = message.substring(1, message.length() - 1);
                                return messageSource.getMessage(key, null, message, LocaleContextHolder.getLocale());
                            }
                            return message;
                        })
                        .collect(Collectors.joining(", "));

                throw new ValidationException(errorMessages);
            }

            return object;
        });
    }
}
