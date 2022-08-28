package com.anynak.diary.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
//https://habr.com/ru/post/528116/
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Locale loc = LocaleContextHolder.getLocale();

    @Autowired
    GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private final MessageSource messageSource;

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        List<String> details = new ArrayList<>();

        List<String> messages = new ArrayList<>();

        messages.add(messageSource.getMessage("userForm.alreadyExists", null, loc));
        details.add(ex.getMessage());
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN,
                "Registration Error",
                details,
                messages);

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(AlreadyLoggedException.class)

    public ResponseEntity<Object> handleUserAlreadyRegistered(Exception ex){
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        List<String> messages = new ArrayList<>();
        messages.add(messageSource.getMessage("login.userAlreadyLogged", null, loc));
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN,
                "logg in is forbidden",
                details,
                messages);

        return ResponseEntityBuilder.build(err);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        List<String> messages = new ArrayList<>();
        messages.add(messageSource.getMessage("access.denied", null, loc));
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN,
                "Access is denied",
                details,
                messages);

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        List<String> messages = new ArrayList<>();
        messages.add(messageSource.getMessage("request.wrong", null, loc));
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Resource Not Found",
                details,
                messages);

        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        List<String> messages = new ArrayList<>();
        messages.add(messageSource.getMessage("request.bad", null, loc));
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Malformed JSON request",
                details,
                messages);

        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> details = new ArrayList<>();
        List<String> messages;
        System.out.println(ex.getAllErrors());
        messages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> "" + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN,
                "Validation Error",
                details,
                messages);

        return ResponseEntityBuilder.build(err);
    }

    //@ExceptionHandler(ValidationException.class)
    //protected ResponseEntity<Object> handleValidationException(
    //        MethodArgumentTypeMismatchException ex,
    //        WebRequest request){
    //    ex.printStackTrace();
    //    List<String> messages = new ArrayList<>();
    //    messages.add(messageSource.getMessage("request.bad", null, loc));
    //    List<String> details = new ArrayList<>();
    //    details.add(ex.getMessage());
    //    ApiError err = new ApiError(
    //            LocalDateTime.now(),
    //            HttpStatus.BAD_REQUEST,
    //            "Type Mismatch",
    //            details, messages);
//
    //    return ResponseEntityBuilder.build(err);
//
    //}

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex) {
        List<String> messages = new ArrayList<>();
        messages.add(messageSource.getMessage("request.bad", null, loc));

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Type Mismatch",
                details, messages);

        return ResponseEntityBuilder.build(err);
    }

    //
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(
            Exception ex) {
        List<String> messages = new ArrayList<>();
        messages.add(messageSource.getMessage("form.input.wrongConstraint", null, loc));
        List<String> details = new ArrayList<>();

        details.add(ex.getMessage());
//
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Constraint Violations",
                details, messages);
//
        return ResponseEntityBuilder.build(err);
    }

    //
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<String> messages = new ArrayList<>();
        messages.add(messageSource.getMessage("request.bad", null, loc));
        List<String> details = new ArrayList<>();
        details.add(ex.getParameterName() + " parameter is missing");
//
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Missing Parameters",
                details,
                messages);
//
        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        details.add(builder.toString());

        List<String> messages = new ArrayList<>();
        messages.add(messageSource.getMessage("request.wrong.mediaType", null, loc));
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                "Unsupported Media Type",
                details,
                messages);

        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));

        List<String> messages = new ArrayList<>();
        messages.add(messageSource.getMessage("request.wrong", null, loc));
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "Method Not Found",
                details,
                messages);

        return ResponseEntityBuilder.build(err);

    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(
            Exception ex) {

        ex.printStackTrace();
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        List<String> messages = new ArrayList<>();
        messages.add(messageSource.getMessage("unknown.exception", null, loc));
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Error occurred",
                details,
                messages);

        return ResponseEntityBuilder.build(err);
    }

}
