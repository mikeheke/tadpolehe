/**
 * 
 */
package mikeheke.tadpole.frm.bds.vo;

import mikeheke.tadpole.frm.bds.util.BdsConstant;

import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 
 *
 * 2011-7-21 下午04:25:16
 */
public class UTF8PostMethod extends PostMethod {

	 public UTF8PostMethod(String url){
         super(url);
     }
     @Override
     public String getRequestCharSet() {
         //return super.getRequestCharSet();
         return BdsConstant.UTF_8;
     }
}
