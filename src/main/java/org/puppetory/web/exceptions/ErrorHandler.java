package org.puppetory.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Central error handler to catch Exceptions in the MCV context and route them to an error page.
 *
 * The following methods are catching specified Exceptions to a defined error page. Unknown
 * exceptions are caught by the root class {@link Exception} and routed to a 500 error page.
 */
@Controller
@ControllerAdvice
public class ErrorHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";
    public static final String MODEL_KEY_TITLE = "title";
    public static final String MODEL_KEY_MSG = "msg";

    /**
     * catch errors to show a 400 error page
     * @return {@link ModelAndView} for an error page
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    @RequestMapping("/400")
    public ModelAndView handle400(){

        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.getModelMap().put(MODEL_KEY_TITLE,"400 - Ihre Anfrage war ung&uuml;ltig.");
        mv.getModelMap().put(MODEL_KEY_MSG,"");
        return mv;
    }

    /**
     * catch errors to show a 401 error page
     * @return {@link ModelAndView} for an error page
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnauthorizedAccessException.class)
    @RequestMapping("/401")
    public ModelAndView handle401(){

        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.getModelMap().put(MODEL_KEY_TITLE,"401 - Authentifizierung erforderlich.");
        mv.getModelMap().put(MODEL_KEY_MSG,"");
        return mv;
    }

    /**
     * catch errors to show a 403 error page
     * @return {@link ModelAndView} for an error page
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = RequestForbiddenException.class)
    @RequestMapping("/403")
    public ModelAndView handle403(){

        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.getModelMap().put(MODEL_KEY_TITLE,"403 - Fehlende Zugriffsberechtigung");
        mv.getModelMap().put(MODEL_KEY_MSG,"");
        return mv;
    }

    /**
     * catch errors to show a 404 error page
     * @return {@link ModelAndView} for an error page
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @RequestMapping("/404")
    public ModelAndView handle404(){

        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.getModelMap().put(MODEL_KEY_TITLE,"404 - Die angefragte Resource kann nicht gefunden werden.");
        mv.getModelMap().put(MODEL_KEY_MSG,"");
        return mv;
    }

    /**
     * catch errors to show a 500 error page
     * @return {@link ModelAndView} for an error page
     */
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
