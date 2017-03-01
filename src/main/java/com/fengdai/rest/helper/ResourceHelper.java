package com.fengdai.rest.helper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.fengdai.base.exception.BusinessException;
import com.fengdai.base.exception.ErrorCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class ResourceHelper {
	public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
	private static Logger logger = LoggerFactory.getLogger(ResourceHelper.class);
	
	public static  Response toError(Exception e){
		
		if(e instanceof BusinessException){
			BusinessException businessException = (BusinessException) e;
			ErrorCode errorCode = businessException.getErrorCode();
			if((businessException.getErrorCode().getDescription()!=null)){
				JSONObject json = new JSONObject();
				JSONObject error = new JSONObject();
				error.put("code", businessException.getCode());
				error.put("description",businessException.getMessage());
				json.put("error", error);
				return toError(json.toString());
			}
		}else{
			logger.error("系统异常500:"+e.getMessage());
			return process("服务器正在疯狂计算，请等待或拨打服务电话4006690685",Status.INTERNAL_SERVER_ERROR);
		}
		/*}else if(e instanceof AuthTokenException){
			401
			return Response.status(Status.UNAUTHORIZED).build();
		*/
		return null;
					
	}
	/**
	 * 统一错误返回
	 * @param errorCode
	 * @return
	 */
	private static Response toError(String message){
		 return process(message,Status.BAD_REQUEST);
	}
	
	/**
	 * 统一错误返回
	 * @param errorCode
	 * @return
	 */
	public static Response toError(ErrorCode errorCode){
		return toError(errorCode.toString());
	}
	
	  /**
     * 成功返回处理
     */
    public static Response returnSuccess(String json) {
        if (json != null) {
            return Response.status(Status.OK).entity(json).type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Status.OK).build();
        }
    }
    
	private static Response process(Object object,Status status){
		if (object != null) {
            return Response.status(status).entity(object).type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(status).build();
        }
	}

	  /**
     * @Description JSON 成功
     */
    public static final String SUCCESS_JSON = "{\"status\":\"success\"}";
    /**
     * @Description JSON 失败
     */
    public static final String FAILURE_JSON = "{\"status\":\"failure\"}";
}
