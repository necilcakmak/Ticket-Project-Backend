package com.ticket.ws.kullanici;

import com.ticket.ws.error.ApiError;
import com.ticket.ws.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KullaniciController {

    @Autowired
    KullaniciService kullaniciService;

    @PostMapping("/api/kayit")
    public GenericResponse kullaniciOlustur(@Valid @RequestBody Kullanici kullanici){
        kullaniciService.save(kullanici);
        return new GenericResponse("kullanici oluşturuldu");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception){
        ApiError error=new ApiError(400,"Validation error","/api/kayit");
        Map<String,String > validationErrors=new HashMap<>();
        for(FieldError fieldError:exception.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());

        }
        error.setValidationErrors(validationErrors);
        return error;
    }

    @GetMapping ("/api/kullanicilar")
    public List<Kullanici>kullaniciListele(){
        return kullaniciService.kullaniciList();
    }


}
