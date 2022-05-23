package com.cy.store.Controller;

import com.cy.store.Controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author lizhenghao
 * @create 2022-02-17-12:10
 */
public class BaseController {
    public static final int OK = 200;

    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);//传递异常的message
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
        } else if (e instanceof InsertException){
            result.setState(5000);
        } else if (e instanceof UserNotFoundException){
            result.setState(5001);
        } else if (e instanceof PasswordNotMatchException){
            result.setState(5002);
        } else if (e instanceof FileEmptyException){
            result.setState(6000);
        }else if (e instanceof FileSizeException){
            result.setState(6001);
        }else if (e instanceof FileTypeException){
            result.setState(6002);
        }else if (e instanceof FileStateException){
            result.setState(6003);
        }else if (e instanceof FileUploadIOException){
            result.setState(6004);
        }else if (e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("用户的收货地址超出上限");
        }else if (e instanceof AddressNotFoundException){
            result.setState(4004);
            result.setMessage("用户收货地址不存在的异常");
        }else if (e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("收货地址数据非法访问的异常");
        }else if (e instanceof ProductNotFoundException){
            result.setState(4006);
            result.setMessage("商品不存在的异常");
        }else if (e instanceof CartNotFoundException){
            result.setState(4006);
            result.setMessage("商品不存在的异常");
        }

        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session
     * @return 当前登录的用户uid
     */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取session对象中的username
     * @param session
     * @return 当前登陆的用户名
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
