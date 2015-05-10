package mikeheke.tadpole.frm.job.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mikeheke.tadpole.frm.base.dao.impl.BaseDao;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.JDBCHelper;
import mikeheke.tadpole.frm.job.vo.ShopPropertyVo;

/**
 * 
 * @author Xie Jinnan
 *
 */
public class ShopAuthDao extends BaseDao {
	
	public List<ShopPropertyVo> findAllShopProps() throws SQLException {
		final String SQL_1 = "select sh.unit_code as unitCode, sh.shop_code as shopCode,sh.shop_name_cn as shopName,sh.shop_head as shopHead, sh.shop_head2 as shopHead2, sh.shop_head_mail as email, sh.prv_head1 as prvHead1, sh.city_head as cityHead from ";
		final String SQL_2 = ".shop_heads sh where sh.shop_code is not null ";
		
		final String UNIT_CODE = "unitCode";
		final String SHOP_CODE = "shopCode";
		final String SHOP_NAME = "shopName";
		final String EMAIL = "email";
		final String SHOP_HEAD = "shopHead";
		final String SHOP_HEAD2 = "shopHead2";
		final String PRV_HEAD1 = "prvHead1";
		final String CITY_HEAD = "cityHead";
		
		String sql = SQL_1 + AppConstant.PORTAL_PRE + SQL_2;

		JDBCHelper jdbcHelper = null;
		ShopPropertyVo vo = null;
		List<ShopPropertyVo> shopList = new ArrayList<ShopPropertyVo>();
		try {
			jdbcHelper = new JDBCHelper(AppConstant.DB2_DATASOURCE_NAME);
			boolean result = jdbcHelper.getFirstDocument(sql);
			if (result) {
				vo = new ShopPropertyVo();
				vo.setUnitCode(jdbcHelper.getItemTrueValue(UNIT_CODE));
				vo.setShopCode(jdbcHelper.getItemTrueValue(SHOP_CODE));
				vo.setShopName(jdbcHelper.getItemTrueValue(SHOP_NAME));
				vo.setEmail(jdbcHelper.getItemTrueValue(EMAIL));
				vo.setShopHead(jdbcHelper.getItemTrueValue(SHOP_HEAD));
				vo.setShopHead2(jdbcHelper.getItemTrueValue(SHOP_HEAD2));
				vo.setPrvHead1(jdbcHelper.getItemTrueValue(PRV_HEAD1));
				vo.setCityHead(jdbcHelper.getItemTrueValue(CITY_HEAD));
				shopList.add(vo);
			}
			while (jdbcHelper.getNextDocument()) {
				vo = new ShopPropertyVo();
				vo.setUnitCode(jdbcHelper.getItemTrueValue(UNIT_CODE));
				vo.setShopCode(jdbcHelper.getItemTrueValue(SHOP_CODE));
				vo.setShopName(jdbcHelper.getItemTrueValue(SHOP_NAME));
				vo.setEmail(jdbcHelper.getItemTrueValue(EMAIL));
				vo.setShopHead(jdbcHelper.getItemTrueValue(SHOP_HEAD));
				vo.setShopHead2(jdbcHelper.getItemTrueValue(SHOP_HEAD2));
				vo.setPrvHead1(jdbcHelper.getItemTrueValue(PRV_HEAD1));
				vo.setCityHead(jdbcHelper.getItemTrueValue(CITY_HEAD));
				shopList.add(vo);
			}
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (jdbcHelper != null) {
				jdbcHelper.closeAll();
			}
		}
		return shopList;
	}
	
	public int executeUpdate(String sql) throws SQLException {
		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(AppConstant.DATASOURCE_NAME);
			return jdbcHelper.executeUpdate(sql);
		} catch (Exception e) {
			return -1;
		} finally {
			if (jdbcHelper != null) {
				jdbcHelper.closeAll();
			}
		}
	}
	
}
