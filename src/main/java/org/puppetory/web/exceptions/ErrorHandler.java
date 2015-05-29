package org.puppetory.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 29.05.15
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
@Controller
@ControllerAdvice
public class ErrorHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";
    public static final String MODEL_KEY_TITLE = "title";
    public static final String MODEL_KEY_MSG = "msg";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    @RequestMapping("/400")
    public ModelAndView handle400(){

        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.getModelMap().put(MODEL_KEY_TITLE,"400 - Ihre Anfrage war ung&uuml;ltig.");
        mv.getModelMap().put(MODEL_KEY_MSG,"");
        return mv;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnauthorizedAccessException.class)
    @RequestMapping("/401")
    public ModelAndView handle401(){

        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.getModelMap().put(MODEL_KEY_TITLE,"401 - Authentifizierung erforderlich.");
        mv.getModelMap().put(MODEL_KEY_MSG,"");
        return mv;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = RequestForbiddenException.class)
    @RequestMapping("/403")
    public ModelAndView handle403(){

        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.getModelMap().put(MODEL_KEY_TITLE,"403 - Fehlende Zugriffsberechtigung");
        mv.getModelMap().put(MODEL_KEY_MSG,"");
        return mv;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @RequestMapping("/404")
    public ModelAndView handle404(){

        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.getModelMap().put(MODEL_KEY_TITLE,"404 - Die angefragte Resource kann nicht gefunden werden.");
        mv.getModelMap().put(MODEL_KEY_MSG,"");
        return mv;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    @RequestMapping("/500")
    public ModelAndView handle500(){

        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.getModelMap().put(MODEL_KEY_TITLE,"500 - Es ist ein Fehler aufgetreten.");
        mv.getModelMap().put(MODEL_KEY_MSG,"Auf dem Server ist ein Fehler aufgetreten. Bitte versuchen Sie es noch einmal oder wenden Sie sich an den Service Desk.");
        return mv;
    }
}
