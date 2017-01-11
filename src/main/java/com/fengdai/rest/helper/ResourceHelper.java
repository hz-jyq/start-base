package com.fengdai.rest.helper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.alibaba.fastjson.JSONObject;
import com.fengdai.base.exception.BusinessException;
import com.fengdai.base.exception.ErrorCode;

public class ResourceHelper {
	public static Response toError(Exception e){
		
		if(e instanceof BusinessException){
			BusinessException businessException = (BusinessException) e;
			ErrorCode errorCode = businessException.getErrorCode();
			if((businessException.getErrorCode().getDescription()!=null)){
				JSONObject json = new JSONObject();
				JSONObject error = new JSONObject();
				error.put("code", errorCode.getCode());
				error.put("description", businessException.getErrorCode().getDescription());
				json.put("error", error);
				return toError(json.toString());
			}
			return toError(errorCode);	
		/*}else if(e instanceof AuthTokenException){
			//401
			return Response.status(Status.UNAUTHORIZED).build();
		}else{
			logger.error("系统异常500:"+e.getMessage());
			return toJsonString("服务器正在疯狂计算，请等待或拨打服务电话4006690685",Status.INTERNAL_SERVER_ERROR);
		}	*/
		}
		return toError("String");
		
			
	}
	/**
	 * 统一错误返回
	 * @param errorCode
	 * @return
	 */
	private static Response toError(String message){
		return Response.status(Status.BAD_REQUEST).entity(message).type(MediaType.APPLICATION_JSON).build();
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

}
