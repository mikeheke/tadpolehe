/**
 * 
 */
package mikeheke.tadpole.frm.query.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import mikeheke.tadpole.frm.base.util.AppConstant;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 *
 * 2011-5-20 上午10:16:18
 */
@Entity
@Table(name = "MSTB_BDS_SCHEMAINFOR", schema = AppConstant.APP_DEAULT_SCHEMA)
public class DataCoding implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1467280606097799354L;

	@Id
	//@GenericGenerator(name="systemUUID",strategy="uuid")
	//@GeneratedValue(generator="systemUUID")
	@Column(name = "BDS_SCHEMAINFOR_ID")
	private String bdsSchemainforId;
	
	@Column(name = "BDS_SCHEMAINFOR_CODE")
	private String bdsSchemainforCode;
	
	@Column(name = "BDS_SCHEMAINFOR_NAME_CNA")
	private String bdsSchemainforNameCna;

	public String getBdsSchemainforId() {
		return bdsSchemainforId;
	}

	public void setBdsSchemainforId(String bdsSchemainforId) {
		this.bdsSchemainforId = bdsSchemainforId;
	}

	public String getBdsSchemainforCode() {
		return bdsSchemainforCode;
	}

	public void setBdsSchemainforCode(String bdsSchemainforCode) {
		this.bdsSchemainforCode = bdsSchemainforCode;
	}

	public String getBdsSchemainforNameCna() {
		return bdsSchemainforNameCna;
	}

	public void setBdsSchemainforNameCna(String bdsSchemainforNameCna) {
		this.bdsSchemainforNameCna = bdsSchemainforNameCna;
	}
	
	
}
