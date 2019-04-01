package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.*;
import sun.bz1.entity.*;
import util.MyBatisUtil;

/**
 * 维修单表
 * 
 * Service
 * 
 * @author WJF on 2018/09/07
 */

@Service
public class OrderTableService {

	@Autowired
	private OrderTableDao orderTableDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private OrderPriceDao orderPriceDao;
	
	@Autowired
	private SnatchDao snatchDao;

	@Autowired
	private BackMoneyDao backMoneyDao;

	@Autowired
	private UserServiceIncomeMoneyDao userServiceIncomeMoneyDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrderDetailConfirmDao orderDetailConfirmDao;

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private EvaluateServiceDao evaluateServiceDao;

	@Autowired
	private EvaluateCustomerDao evaluateCustomerDao;

	@Autowired
	private ApplyCheckDao applyCheckDao;

	@Autowired
	private UserPaymentDao userPaymentDao;

	private Logger logger = Logger.getLogger(OrderTableService.class);
	
	/**
   	 * 根据OrderTable实体添加
   	 * 
   	 * @param orderTable
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/07
   	 */
	public int insertByOrderTable(OrderTable orderTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.insertByOrderTable(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--insertByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体更新
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int updateByOrderTable(OrderTable orderTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.updateByOrderTable(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--updateByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int selectCountByOrderTable(OrderTable orderTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectCountByOrderTable(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectCountByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public List<OrderTable> selectByOrderTable(OrderTable orderTable) {
		List<OrderTable> orderTableList = new ArrayList<OrderTable>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderTableList = orderTableDao.selectByOrderTable(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectByOrderTable--error:" + e.getMessage());
		}

		return orderTableList;
	}

	/**
	 * 根据OrderTable实体模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int selectCountBySelectData(OrderTable orderTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectCountBySelectData(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public List<OrderTable> selectBySelectData(OrderTable orderTable) {
		List<OrderTable> orderTableList = new ArrayList<OrderTable>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderTableList = orderTableDao.selectBySelectData(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectBySelectData--error:" + e.getMessage());
		}

		return orderTableList;
	}
	
	/**
	 * 根据OrderTable实体查询，返回OrderTableAndOrderDetailList实体类
	 * 
   	 * 查询数量(OrderTable的数量)
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	public int selectTwoTablesCountByOrderTable(OrderTable orderTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectCountByOrderTable(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectTwoTablesCountByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体查询，返回OrderTableAndOrderDetailList实体类
	 * 
	 * 可以进行分页查询(按OrderTable分页查询)
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	public List<OrderTableAndOrderDetailList> selectTwoTablesByOrderTable(OrderTable orderTable) {
		// 返回数据List
		List<OrderTableAndOrderDetailList> OTAODLList = new ArrayList<OrderTableAndOrderDetailList>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			
			// 返回数据
			OrderTableAndOrderDetailList OTAODL = new OrderTableAndOrderDetailList();

			// 查询维修单表
			List<OrderTable> orderTableList = new ArrayList<OrderTable>();
			orderTableList = orderTableDao.selectByOrderTable(session, orderTable);
			
			if (orderTableList.size() == 0) {
				OTAODL.setOrderTable(null); // 添加维修单表
				OTAODL.setOrderDetailList(null); // 添加维修单详情表
				
				OTAODLList.add(OTAODL); // 为List添加数据
			} else {
				// 循环查询到的维修单表，根据维修单ID，查询维修单详情表
				for (int i = 0; i < orderTableList.size(); i++) {
					// 根据维修单ID，查询维修单详情表
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrderid(orderTableList.get(i).getOrderid());
					List<OrderDetail> orderDetailList = null;
					orderDetailList = orderDetailDao.selectByOrderDetail(session, orderDetail); // 查询
					
					OTAODL.setOrderTable(orderTableList.get(i)); // 添加维修单表
					OTAODL.setOrderDetailList(orderDetailList); // 添加维修单详情表
					
					OTAODLList.add(OTAODL); // 为List添加数据
				}
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectTwoTablesByOrderTable--error:" + e.getMessage());
		}

		return OTAODLList;
	}

	/**
	 * 根据OrderTable实体查询，返回OrderTableAndOrderDetailConfirmList实体类
	 *
	 * 查询数量(OrderTable的数量)
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/11/26
	 */
	public int selectOrderTableConfirmCountByOrderTable(OrderTable orderTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectCountByOrderTable(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectOrderTableConfirmCountByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体查询，返回OrderTableAndOrderDetailConfirmList实体类
	 *
	 * 可以进行分页查询(按OrderTable分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/11/26
	 */
	public List<OrderTableAndOrderDetailConfirmList> selectOrderTableConfirmByOrderTable(OrderTable orderTable) {
		// 返回数据List
		List<OrderTableAndOrderDetailConfirmList> OTAODLList = new ArrayList<OrderTableAndOrderDetailConfirmList>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 返回数据
			OrderTableAndOrderDetailConfirmList OTAODL = new OrderTableAndOrderDetailConfirmList();

			// 查询维修单表
			List<OrderTable> orderTableList = new ArrayList<OrderTable>();
			orderTableList = orderTableDao.selectByOrderTable(session, orderTable);

			if (orderTableList.size() == 0) {
				OTAODL.setOrderTable(null); // 添加维修单表
				OTAODL.setOrderDetailConfirmList(null); // 添加维修单详情表

				OTAODLList.add(OTAODL); // 为List添加数据
			} else {
				// 循环查询到的维修单表，根据维修单ID，查询维修单详情表
				for (int i = 0; i < orderTableList.size(); i++) {
					// 根据维修单ID，查询维修单详情表
					OrderDetailConfirm orderDetailConfirm = new OrderDetailConfirm();
					orderDetailConfirm.setOrderid(orderTableList.get(i).getOrderid());
					List<OrderDetailConfirm> orderDetailConfirmList = null;
					orderDetailConfirmList = orderDetailConfirmDao.selectByOrderDetailConfirm(session, orderDetailConfirm); // 查询

					OTAODL.setOrderTable(orderTableList.get(i)); // 添加维修单表
					OTAODL.setOrderDetailConfirmList(orderDetailConfirmList); // 添加维修单详情表

					OTAODLList.add(OTAODL); // 为List添加数据
				}
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectOrderTableConfirmByOrderTable--error:" + e.getMessage());
		}

		return OTAODLList;
	}
	
	/**
	 * 根据OrderTableAndBuildingType实体联表查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
   	 * 查询数量(OrderTableAndBuildingType的数量)
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/14
	 */
	public int selectThreeTablesCountByUnionData(OrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectTwoTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/14
	 */
	public List<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablesByUnionData(OrderTableAndBuildingType unionData) {
		// 返回数据List
		List<OrderTableAndBuildingTypeAndOrderDetailList> OTAODLList = new ArrayList<OrderTableAndBuildingTypeAndOrderDetailList>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			


			// 查询维修单表
			List<OrderTableAndBuildingType> orderTableList = new ArrayList<OrderTableAndBuildingType>();
			orderTableList = orderTableDao.selectTwoTablesByUnionData(session, unionData);
			
			if (orderTableList.size() == 0) {
				// 返回数据
				OrderTableAndBuildingTypeAndOrderDetailList OTAODL = new OrderTableAndBuildingTypeAndOrderDetailList();
				OTAODL.setOrderTableAndBuildingType(null); // 添加维修单表
				OTAODL.setOrderDetailList(null); // 添加维修单详情表
				
				OTAODLList.add(OTAODL); // 为List添加数据
			} else {
				// 循环查询到的维修单表，根据维修单ID，查询维修单详情表
				for (int i = 0; i < orderTableList.size(); i++) {
					// 根据维修单ID，查询维修单详情表
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrderid(orderTableList.get(i).getOrderid());
					List<OrderDetail> orderDetailList = null;
					orderDetailList = orderDetailDao.selectByOrderDetail(session, orderDetail); // 查询

					// 返回数据
					OrderTableAndBuildingTypeAndOrderDetailList OTAODL = new OrderTableAndBuildingTypeAndOrderDetailList();
					OTAODL.setOrderTableAndBuildingType(orderTableList.get(i)); // 添加维修单表
					OTAODL.setOrderDetailList(orderDetailList); // 添加维修单详情表

					OTAODLList.add(OTAODL); // 为List添加数据
				}
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return OTAODLList;
	}

	/**
	 * 生成订单报表时使用
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/09/14
	 */
	public List<OrderTableAndBuildingType> selectThreeTablesByUnionDataReport(OrderTableAndBuildingType unionData) {
		// 返回数据List
		List<OrderTableAndBuildingTypeAndOrderDetailListReport> OTAODLList = new ArrayList<OrderTableAndBuildingTypeAndOrderDetailListReport>();
		List<OrderTableAndBuildingType> orderTableList = new ArrayList<OrderTableAndBuildingType>();
		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 返回数据
			OrderTableAndBuildingTypeAndOrderDetailListReport OTAODL = new OrderTableAndBuildingTypeAndOrderDetailListReport();

			// 查询维修单表

			orderTableList = orderTableDao.selectTwoTablesByUnionData(session, unionData);

			if (orderTableList.size() == 0) {
				OTAODL.setOrderTableAndBuildingType(null); // 添加维修单表
				OTAODL.setOrderDetailList(null); // 添加维修单详情表

				OTAODLList.add(OTAODL); // 为List添加数据
			} else {
                OrderTableAndBuildingType ordertotal = new OrderTableAndBuildingType();
                OrderTableAndBuildingType ordersvg = new OrderTableAndBuildingType();
			    //客户评价维修工的总分
                double evaluateServicetotalscore = 0;
                //维修工评价客户总分
                double evaluateCustomertotalscore = 0;
                //总价
                double totalmoney = 0;
                //利润
                double profittotalmoney = 0;

				// 循环查询到的维修单表，根据维修单ID，查询维修单详情表
				for (int i = 0; i < orderTableList.size(); i++) {
					// 根据维修单ID，查询维修单详情表
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrderid(orderTableList.get(i).getOrderid());
					List<OrderDetail> orderDetailList = null;
					orderDetailList = orderDetailDao.selectByOrderDetail(session, orderDetail); // 查询

					OTAODL.setOrderDetailList(orderDetailList); // 添加维修单详情表
					if (orderDetailList.size() > 0) {
						String orderAllProject = "";
						for (int j = 0; j < orderDetailList.size(); j++){
							orderAllProject = orderAllProject + ((j+1) + "、" + orderDetailList.get(j).getGoodsname() + "-" + orderDetailList.get(j).getProjectname() + "-" + orderDetailList.get(j).getOrderdetailnumber() + "\n");
						}
						orderTableList.get(i).setOrderallproject(orderAllProject);
					}


					//通过代码查询区域名称
					if (null != orderTableList.get(i).getOrderposition() && !orderTableList.get(i).getOrderposition().equals("")){
						Area area = new Area();
						area.setPagenumber(-1);//不分页
						area.setAreaprovincecode(orderTableList.get(i).getOrderposition());//城市代码
						List<Area> areaList = areaDao.selectByArea(session, area);
						if (areaList.size() > 0){
							orderTableList.get(i).setAreaname(areaList.get(0).getAreaprovince());//区域名称
						}
					}

					//查询维修、安装人员
					if (null != orderTableList.get(i).getOrderserviceuserid() && !orderTableList.get(i).getOrderserviceuserid().equals("")){
						UserAndUserServiceAndUserCustomer user = new UserAndUserServiceAndUserCustomer();
						user.setPagenumber(-1);//不分页
						user.setUserid(orderTableList.get(i).getOrderserviceuserid());//维修人员userid
						List<UserAndUserServiceAndUserCustomer> userList = userDao.selectThreeTablesByUnionData(session, user);
						if (userList.size() > 0) {
							orderTableList.get(i).setOrderserviceusername(userList.get(0).getUserrealname());//添加维修人员名字
						}
					}

					//查询客户评价维修人员
					EvaluateServiceAndOrderTableAndUser evaluateService = new EvaluateServiceAndOrderTableAndUser();
					evaluateService.setPagenumber(-1);//不分页
					evaluateService.setOrderid(orderTableList.get(i).getOrderid());//订单编号
					List<EvaluateServiceAndOrderTableAndUser> evaluateServiceList = evaluateServiceDao.selectThreeTablesByUnionData(session, evaluateService);
					if (evaluateServiceList.size() > 0) {
						String evaluateServiceStr = "";
						String [] evaluateservicesetupname = evaluateServiceList.get(0).getEvaluateservicesetupname().split(",");
						String [] evaluateservicesetupscore = evaluateServiceList.get(0).getEvaluateservicesetupscore().split(",");
						//需要返回客户评价维修人员的信息
						List<EvaluateServiceSetup> evaluateServiceSetupList = new ArrayList<EvaluateServiceSetup>();
						for (int j =0; j < evaluateservicesetupname.length; j++){
							EvaluateServiceSetup evaluateServiceSetup = new EvaluateServiceSetup();
							evaluateServiceSetup.setEvaluateservicesetupname(evaluateservicesetupname[j]);
							evaluateServiceSetup.setEvaluateservicesetupscore(Integer.parseInt(evaluateservicesetupscore[j]));
							evaluateServiceSetupList.add(evaluateServiceSetup);

							//添加字符串
							evaluateServiceStr = evaluateServiceStr + ((j+1) + "、" + evaluateservicesetupname[j] + "：" + evaluateservicesetupscore[j] + "分\n");
						}
						//评价总分相加
                        evaluateServicetotalscore = evaluateServicetotalscore + evaluateServiceList.get(0).getEvaluateservicescore();

						orderTableList.get(i).setEvaluateServiceSetup("评论得分：" + evaluateServiceList.get(0).getEvaluateservicescore() + "\n评论项目：\n" + evaluateServiceStr + "评论内容：\n" + evaluateServiceList.get(0).getEvaluateserviceopinion() );
						OTAODL.setEvaluateServiceSetupList(evaluateServiceSetupList);
					}

					//查询维修人员评价客户
					EvaluateCustomerAndOrderTableAndUser evaluateCustomer = new EvaluateCustomerAndOrderTableAndUser();
					evaluateCustomer.setPagenumber(-1);//不分页
					evaluateCustomer.setOrderid(orderTableList.get(i).getOrderid());//订单编号
					List<EvaluateCustomerAndOrderTableAndUser> evaluateCustomerList = evaluateCustomerDao.selectThreeTablesByUnionData(session, evaluateCustomer);
					if (evaluateCustomerList.size() > 0) {
						String evaluateCustomerStr = "";
						String [] evaluatecustomersetupname = evaluateCustomerList.get(0).getEvaluatecustomersetupname().split(",");
						String [] evaluatecustomersetupscore = evaluateCustomerList.get(0).getEvaluatecustomersetupscore().split(",");
						//需要返回客户评价维修人员的信息
						List<EvaluateCustomerSetup> evaluateCustomerSetupList = new ArrayList<EvaluateCustomerSetup>();
						for (int j =0; j < evaluatecustomersetupname.length; j++){
							EvaluateCustomerSetup evaluateCustomerSetup = new EvaluateCustomerSetup();
							evaluateCustomerSetup.setEvaluatecustomersetupname(evaluatecustomersetupname[j]);
							evaluateCustomerSetup.setEvaluatecustomersetupscore(Integer.parseInt(evaluatecustomersetupscore[j]));
							evaluateCustomerSetupList.add(evaluateCustomerSetup);

							//添加字符串
							evaluateCustomerStr = evaluateCustomerStr + ((j+1) + "、" + evaluatecustomersetupname[j] + "：" + evaluatecustomersetupscore[j] + "分\r\n");
						}
                        evaluateCustomertotalscore = evaluateCustomertotalscore + evaluateCustomerList.get(0).getEvaluatecustomerscore();

						orderTableList.get(i).setEvaluateCustomerSetup("评论得分：" + evaluateCustomerList.get(0).getEvaluatecustomerscore() + "\r\n评论项目：\r\n" + evaluateCustomerStr + "评论内容：\r\n" + evaluateCustomerList.get(0).getEvaluatecustomeropinion() );
						OTAODL.setEvaluateCustomerSetupList(evaluateCustomerSetupList);
					}

					//查询验收状况
					ApplyCheckAndOrderTableAndUser applyCheckAndOrderTableAndUser = new ApplyCheckAndOrderTableAndUser();
					applyCheckAndOrderTableAndUser.setPagenumber(-1);//不分页
					applyCheckAndOrderTableAndUser.setOrderid(orderTableList.get(i).getOrderid());//订单编号
					List<ApplyCheckAndOrderTableAndUser> applyCheckList = applyCheckDao.selectThreeTablesByUnionData(session, applyCheckAndOrderTableAndUser);//查询
					if (applyCheckList.size() > 0){
						if (applyCheckList.get(0).getApplycheckstate().equals("HG")){
							orderTableList.get(i).setOrderapplycheckstate("合格");
						} else if (applyCheckList.get(0).getApplycheckstate().equals("SQYS")){
							orderTableList.get(i).setOrderapplycheckstate("申请验收中");
						} else {
							orderTableList.get(i).setOrderapplycheckstate("不合格");
						}
					}

                    if (null != orderTableList.get(i).getOrdertype() && orderTableList.get(i).getOrdertype().equals("PD")){
                        totalmoney = totalmoney + orderTableList.get(i).getOrderintercepttotalmoney();
						if (null != orderTableList.get(i).getOrderpricetotalmoney() && !orderTableList.get(i).getOrderpricetotalmoney().equals("") && 0 != orderTableList.get(i).getOrderpricetotalmoney()) {
							profittotalmoney = profittotalmoney + (orderTableList.get(i).getOrderintercepttotalmoney() - orderTableList.get(i).getOrderpricetotalmoney());
						}
                    } else if (null != orderTableList.get(i).getOrdertype() && orderTableList.get(i).getOrdertype().equals("QD")) {
                    	if (null != orderTableList.get(i).getOrderpricetotalmoney() && !orderTableList.get(i).getOrderpricetotalmoney().equals("") && 0 != orderTableList.get(i).getOrderpricetotalmoney()) {
							totalmoney = totalmoney + orderTableList.get(i).getOrderpricetotalmoney();
						}
                    }

					OTAODL.setOrderTableAndBuildingType(orderTableList.get(i)); // 添加维修单表

					OTAODLList.add(OTAODL); // 为List添加数据

				}

				double size = orderTableList.size();
                //订单总价合计、平均价

                ordertotal.setOrderpricetotalmoney(totalmoney);
                ordersvg.setOrderpricetotalmoney((totalmoney/size));

                //利润合计、平均价

                ordertotal.setProfittotalmoney(profittotalmoney);
                ordersvg.setProfittotalmoney((profittotalmoney/size));

                //客户评价合计、平均价

                ordersvg.setEvaluateServiceSetup(evaluateServicetotalscore/size + "");

                //维修工评价合计、平均价

                ordersvg.setEvaluateCustomerSetup(evaluateCustomertotalscore/size + "");

				//添加合计、平均数
				ordertotal.setOrderstate("合计");
				ordersvg.setOrderstate("平台平均值");

				//添加订单类型（合计时的类型）
				ordertotal.setOrdertype("QDPD");
				ordersvg.setOrdertype("QDPD");

				orderTableList.add(ordertotal);
				orderTableList.add(ordersvg);

			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesByUnionDataReport--error:" + e.getMessage());
		}

		return orderTableList;
	}

	/**
	 * 生成订单报表时使用(导出报表)
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/09/14
	 */
	public List<OrderTableAndBuildingType> selectThreeTablesByUnionDataReportExcel(OrderTableAndBuildingType unionData) {
		// 返回数据List
		List<OrderTableAndBuildingTypeAndOrderDetailListReport> OTAODLList = new ArrayList<OrderTableAndBuildingTypeAndOrderDetailListReport>();
		List<OrderTableAndBuildingType> orderTableList = new ArrayList<OrderTableAndBuildingType>();
		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 返回数据
			OrderTableAndBuildingTypeAndOrderDetailListReport OTAODL = new OrderTableAndBuildingTypeAndOrderDetailListReport();

			// 查询维修单表

			orderTableList = orderTableDao.selectTwoTablesByUnionData(session, unionData);

			if (orderTableList.size() == 0) {
				OTAODL.setOrderTableAndBuildingType(null); // 添加维修单表
				OTAODL.setOrderDetailList(null); // 添加维修单详情表

				OTAODLList.add(OTAODL); // 为List添加数据
			} else {
				OrderTableAndBuildingType ordertotal = new OrderTableAndBuildingType();
				OrderTableAndBuildingType ordersvg = new OrderTableAndBuildingType();
				//客户评价维修工的总分
				double evaluateServicetotalscore = 0;
				//维修工评价客户总分
				double evaluateCustomertotalscore = 0;
				//总价
				double totalmoney = 0;
				//利润
				double profittotalmoney = 0;

				// 循环查询到的维修单表，根据维修单ID，查询维修单详情表
				for (int i = 0; i < orderTableList.size(); i++) {
					// 根据维修单ID，查询维修单详情表
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrderid(orderTableList.get(i).getOrderid());
					List<OrderDetail> orderDetailList = null;
					orderDetailList = orderDetailDao.selectByOrderDetail(session, orderDetail); // 查询

					OTAODL.setOrderDetailList(orderDetailList); // 添加维修单详情表
					if (orderDetailList.size() > 0) {
						String orderAllProject = "";
						for (int j = 0; j < orderDetailList.size(); j++){
							orderAllProject = orderAllProject + ((j+1) + "、" + orderDetailList.get(j).getGoodsname() + "-" + orderDetailList.get(j).getProjectname() + "-" + orderDetailList.get(j).getOrderdetailnumber() + "；");
						}
						orderTableList.get(i).setOrderallproject(orderAllProject);
					}


					//通过代码查询区域名称
					if (null != orderTableList.get(i).getOrderposition() && !orderTableList.get(i).getOrderposition().equals("")){
						Area area = new Area();
						area.setPagenumber(-1);//不分页
						area.setAreaprovincecode(orderTableList.get(i).getOrderposition());//城市代码
						List<Area> areaList = areaDao.selectByArea(session, area);
						if (areaList.size() > 0){
							orderTableList.get(i).setAreaname(areaList.get(0).getAreaprovince());//区域名称
						}
					}

					//查询维修、安装人员
					if (null != orderTableList.get(i).getOrderserviceuserid() && !orderTableList.get(i).getOrderserviceuserid().equals("")){
						UserAndUserServiceAndUserCustomer user = new UserAndUserServiceAndUserCustomer();
						user.setPagenumber(-1);//不分页
						user.setUserid(orderTableList.get(i).getOrderserviceuserid());//维修人员userid
						List<UserAndUserServiceAndUserCustomer> userList = userDao.selectThreeTablesByUnionData(session, user);
						if (userList.size() > 0) {
							orderTableList.get(i).setOrderserviceusername(userList.get(0).getUserrealname());//添加维修人员名字
						}
					}

					//查询客户评价维修人员
					EvaluateServiceAndOrderTableAndUser evaluateService = new EvaluateServiceAndOrderTableAndUser();
					evaluateService.setPagenumber(-1);//不分页
					evaluateService.setOrderid(orderTableList.get(i).getOrderid());//订单编号
					List<EvaluateServiceAndOrderTableAndUser> evaluateServiceList = evaluateServiceDao.selectThreeTablesByUnionData(session, evaluateService);
					if (evaluateServiceList.size() > 0) {
						String evaluateServiceStr = "";
						String [] evaluateservicesetupname = evaluateServiceList.get(0).getEvaluateservicesetupname().split(",");
						String [] evaluateservicesetupscore = evaluateServiceList.get(0).getEvaluateservicesetupscore().split(",");
						//需要返回客户评价维修人员的信息
						List<EvaluateServiceSetup> evaluateServiceSetupList = new ArrayList<EvaluateServiceSetup>();
						for (int j =0; j < evaluateservicesetupname.length; j++){
							EvaluateServiceSetup evaluateServiceSetup = new EvaluateServiceSetup();
							evaluateServiceSetup.setEvaluateservicesetupname(evaluateservicesetupname[j]);
							evaluateServiceSetup.setEvaluateservicesetupscore(Integer.parseInt(evaluateservicesetupscore[j]));
							evaluateServiceSetupList.add(evaluateServiceSetup);

							//添加字符串
							evaluateServiceStr = evaluateServiceStr + ((j+1) + "、" + evaluateservicesetupname[j] + "：" + evaluateservicesetupscore[j] + "分；");
						}
						//评价总分相加
						evaluateServicetotalscore = evaluateServicetotalscore + evaluateServiceList.get(0).getEvaluateservicescore();

						orderTableList.get(i).setEvaluateServiceSetup("评论得分：" + evaluateServiceList.get(0).getEvaluateservicescore() + "；评论项目：" + evaluateServiceStr + "评论内容：" + evaluateServiceList.get(0).getEvaluateserviceopinion() );
						OTAODL.setEvaluateServiceSetupList(evaluateServiceSetupList);
					}

					//查询维修人员评价客户
					EvaluateCustomerAndOrderTableAndUser evaluateCustomer = new EvaluateCustomerAndOrderTableAndUser();
					evaluateCustomer.setPagenumber(-1);//不分页
					evaluateCustomer.setOrderid(orderTableList.get(i).getOrderid());//订单编号
					List<EvaluateCustomerAndOrderTableAndUser> evaluateCustomerList = evaluateCustomerDao.selectThreeTablesByUnionData(session, evaluateCustomer);
					if (evaluateCustomerList.size() > 0) {
						String evaluateCustomerStr = "";
						String [] evaluatecustomersetupname = evaluateCustomerList.get(0).getEvaluatecustomersetupname().split(",");
						String [] evaluatecustomersetupscore = evaluateCustomerList.get(0).getEvaluatecustomersetupscore().split(",");
						//需要返回客户评价维修人员的信息
						List<EvaluateCustomerSetup> evaluateCustomerSetupList = new ArrayList<EvaluateCustomerSetup>();
						for (int j =0; j < evaluatecustomersetupname.length; j++){
							EvaluateCustomerSetup evaluateCustomerSetup = new EvaluateCustomerSetup();
							evaluateCustomerSetup.setEvaluatecustomersetupname(evaluatecustomersetupname[j]);
							evaluateCustomerSetup.setEvaluatecustomersetupscore(Integer.parseInt(evaluatecustomersetupscore[j]));
							evaluateCustomerSetupList.add(evaluateCustomerSetup);

							//添加字符串
							evaluateCustomerStr = evaluateCustomerStr + ((j+1) + "、" + evaluatecustomersetupname[j] + "：" + evaluatecustomersetupscore[j] + "分；");
						}
						evaluateCustomertotalscore = evaluateCustomertotalscore + evaluateCustomerList.get(0).getEvaluatecustomerscore();

						orderTableList.get(i).setEvaluateCustomerSetup("评论得分：" + evaluateCustomerList.get(0).getEvaluatecustomerscore() + "；评论项目：" + evaluateCustomerStr + "评论内容：" + evaluateCustomerList.get(0).getEvaluatecustomeropinion() );
						OTAODL.setEvaluateCustomerSetupList(evaluateCustomerSetupList);
					}

					//查询验收状况
					ApplyCheckAndOrderTableAndUser applyCheckAndOrderTableAndUser = new ApplyCheckAndOrderTableAndUser();
					applyCheckAndOrderTableAndUser.setPagenumber(-1);//不分页
					applyCheckAndOrderTableAndUser.setOrderid(orderTableList.get(i).getOrderid());//订单编号
					List<ApplyCheckAndOrderTableAndUser> applyCheckList = applyCheckDao.selectThreeTablesByUnionData(session, applyCheckAndOrderTableAndUser);//查询
					if (applyCheckList.size() > 0){
						if (applyCheckList.get(0).getApplycheckstate().equals("HG")){
							orderTableList.get(i).setOrderapplycheckstate("合格");
						} else if (applyCheckList.get(0).getApplycheckstate().equals("SQYS")){
							orderTableList.get(i).setOrderapplycheckstate("申请验收中");
						} else {
							orderTableList.get(i).setOrderapplycheckstate("不合格");
						}
					}

					if (null != orderTableList.get(i).getOrdertype() && orderTableList.get(i).getOrdertype().equals("PD")){
						totalmoney = totalmoney + orderTableList.get(i).getOrderintercepttotalmoney();
						if (null != orderTableList.get(i).getOrderpricetotalmoney() && !orderTableList.get(i).getOrderpricetotalmoney().equals("") && 0 != orderTableList.get(i).getOrderpricetotalmoney()) {
							profittotalmoney = profittotalmoney + (orderTableList.get(i).getOrderintercepttotalmoney() - orderTableList.get(i).getOrderpricetotalmoney());
						}
					} else if (null != orderTableList.get(i).getOrdertype() && orderTableList.get(i).getOrdertype().equals("QD")) {
						if (null != orderTableList.get(i).getOrderpricetotalmoney() && !orderTableList.get(i).getOrderpricetotalmoney().equals("") && 0 != orderTableList.get(i).getOrderpricetotalmoney()) {
							totalmoney = totalmoney + orderTableList.get(i).getOrderpricetotalmoney();
						}
					}

					OTAODL.setOrderTableAndBuildingType(orderTableList.get(i)); // 添加维修单表

					OTAODLList.add(OTAODL); // 为List添加数据

				}

				double size = orderTableList.size();
				//订单总价合计、平均价

				ordertotal.setOrderpricetotalmoney(totalmoney);
				ordersvg.setOrderpricetotalmoney((totalmoney/size));

				//利润合计、平均价

				ordertotal.setProfittotalmoney(profittotalmoney);
				ordersvg.setProfittotalmoney((profittotalmoney/size));

				//客户评价合计、平均价

				ordersvg.setEvaluateServiceSetup(evaluateServicetotalscore/size + "");

				//维修工评价合计、平均价

				ordersvg.setEvaluateCustomerSetup(evaluateCustomertotalscore/size + "");

				//添加合计、平均数
				ordertotal.setOrderstate("合计");
				ordersvg.setOrderstate("平台平均值");

				//添加订单类型（合计时的类型）
				ordertotal.setOrdertype("QDPD");
				ordersvg.setOrdertype("QDPD");

				orderTableList.add(ordertotal);
				orderTableList.add(ordersvg);

			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesByUnionDataReport--error:" + e.getMessage());
		}

		return orderTableList;
	}


	/**
	 * 这个方法主要是针对竞标，显示的单价是维修工自己维护的单价
	 * 
	 * 根据OrderTableAndBuildingType实体联表查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
   	 * 查询数量(OrderTableAndBuildingType的数量)
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/10/10
	 */
	public int selectThreeTablesCountByUnionDataAndPrice(OrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectTwoTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 这个方法主要是针对竞标，显示的单价是维修工自己维护的单价
	 * 
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/10/10
	 */
	public List<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablesByUnionDataAndPrice(OrderTableAndBuildingType unionData) {
		// 返回数据List
		List<OrderTableAndBuildingTypeAndOrderDetailList> OTAODLList = new ArrayList<OrderTableAndBuildingTypeAndOrderDetailList>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			
			// 返回数据
			OrderTableAndBuildingTypeAndOrderDetailList OTAODL = new OrderTableAndBuildingTypeAndOrderDetailList();

			// 查询维修单表
			List<OrderTableAndBuildingType> orderTableList = new ArrayList<OrderTableAndBuildingType>();
			orderTableList = orderTableDao.selectTwoTablesByUnionData(session, unionData);
			
			if (orderTableList.size() == 0) {
				OTAODL.setOrderTableAndBuildingType(null); // 添加维修单表
				OTAODL.setOrderDetailList(null); // 添加维修单详情表
				
				OTAODLList.add(OTAODL); // 为List添加数据
			} else {
				// 循环查询到的维修单表，根据维修单ID，查询维修单详情表
				for (int i = 0; i < orderTableList.size(); i++) {
					// 根据维修单ID，查询维修单详情表
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrderid(orderTableList.get(i).getOrderid());
					List<OrderDetail> orderDetailList = null;
					orderDetailList = orderDetailDao.selectByOrderDetail(session, orderDetail); // 查询
					//通过当前竞标人、产品ID、项目ID、查询维修工自己设置的单价
					for (int j = 0; j < orderDetailList.size(); j++) {
						PriceAndGoodsAndProjectAndUser priceAndGoodsAndProjectAndUser = new PriceAndGoodsAndProjectAndUser();
						priceAndGoodsAndProjectAndUser.setPagenumber(-1);//不分页
						priceAndGoodsAndProjectAndUser.setGoodsid(orderDetailList.get(j).getGoodsid());//产品ID
						priceAndGoodsAndProjectAndUser.setProjectid(orderDetailList.get(j).getProjectid());//项目ID
						priceAndGoodsAndProjectAndUser.setPriceareacode(orderTableList.get(i).getOrderposition());//区域
						priceAndGoodsAndProjectAndUser.setPricepropertycompanyid(orderTableList.get(i).getOrderpropertycompanyid());//地产公司
						priceAndGoodsAndProjectAndUser.setPricebuildingtypeid(orderTableList.get(i).getBuildingtypeid());//建筑类型
						priceAndGoodsAndProjectAndUser.setPricetype(orderTableList.get(i).getOrderprojecttype());//类型
						priceAndGoodsAndProjectAndUser.setPriceupdateuserid(unionData.getCurrentuserid());//当前操作人
						List<PriceAndGoodsAndProjectAndUser> priceList = priceService.selectFourTablesByUnionData(priceAndGoodsAndProjectAndUser); // 查询数据
						double money = 0;
						if (priceList.size() == 0) {
							orderDetailList.get(j).setOrderpricemoney(money);
						} else {
							orderDetailList.get(j).setOrderpricemoney(priceList.get(0).getPricemoney());//维修工自己维护的单价
						}
					}
					OTAODL.setOrderTableAndBuildingType(orderTableList.get(i)); // 添加维修单表
					OTAODL.setOrderDetailList(orderDetailList); // 添加维修单详情表
					
					OTAODLList.add(OTAODL); // 为List添加数据
				}
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return OTAODLList;
	}
	
	/**
	 * 在申请验收时的详情中查看本次订单中的单价
	 * 
	 * 根据OrderTableAndBuildingType实体联表查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
   	 * 查询数量(OrderTableAndBuildingType的数量)
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/10/10
	 */
	public int selectThreeTablesCountByUnionDataAndCheckPrice(OrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectTwoTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 在申请验收时的详情中查看本次订单中的单价
	 * 
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/10/10
	 */
	public List<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablestByUnionDataAndCheckPrice(OrderTableAndBuildingType unionData) {
		// 返回数据List
		List<OrderTableAndBuildingTypeAndOrderDetailList> OTAODLList = new ArrayList<OrderTableAndBuildingTypeAndOrderDetailList>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			
			// 返回数据
			OrderTableAndBuildingTypeAndOrderDetailList OTAODL = new OrderTableAndBuildingTypeAndOrderDetailList();

			// 查询维修单表
			List<OrderTableAndBuildingType> orderTableList = new ArrayList<OrderTableAndBuildingType>();
			orderTableList = orderTableDao.selectTwoTablesByUnionData(session, unionData);
			
			if (orderTableList.size() == 0) {
				OTAODL.setOrderTableAndBuildingType(null); // 添加维修单表
				OTAODL.setOrderDetailList(null); // 添加维修单详情表
				
				OTAODLList.add(OTAODL); // 为List添加数据
			} else {
				// 循环查询到的维修单表，根据维修单ID，查询维修单详情表
				for (int i = 0; i < orderTableList.size(); i++) {
					
					double orderpricetotalmoney = 0;//维修工显示的总价
					// 根据维修单ID，查询维修单详情表
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrderid(orderTableList.get(i).getOrderid());
					List<OrderDetail> orderDetailList = null;
					orderDetailList = orderDetailDao.selectByOrderDetail(session, orderDetail); // 查询
					//通过当前竞标人、产品ID、项目ID、查询维修工自己设置的单价
					for (int j = 0; j < orderDetailList.size(); j++) {
						OrderPriceAndGoodsAndProjectAndUser orderPriceAndGoodsAndProjectAndUser = new OrderPriceAndGoodsAndProjectAndUser();
						orderPriceAndGoodsAndProjectAndUser.setOrderid(orderTableList.get(i).getOrderid());//订单ID
						orderPriceAndGoodsAndProjectAndUser.setGoodsid(orderDetailList.get(j).getGoodsid());//产品ID
						orderPriceAndGoodsAndProjectAndUser.setProjectid(orderDetailList.get(j).getProjectid());//项目ID
						orderPriceAndGoodsAndProjectAndUser.setOrderpriceupdateuserid(orderTableList.get(i).getOrderserviceuserid());//当前操作人
						List<OrderPriceAndGoodsAndProjectAndUser> priceList = orderPriceDao.selectFourTablesByUnionData(session, orderPriceAndGoodsAndProjectAndUser); // 查询数据
						double money = 0;
						if (priceList.size() == 0) {
							orderDetailList.get(j).setOrderpricemoney(money);
						} else {
							orderDetailList.get(j).setOrderpricemoney(priceList.get(0).getOrderpricemoney());//维修工自己维护的单价
							orderDetailList.get(j).setOrderdetailpricetotalmoney(priceList.get(0).getOrderpricemoney() * orderDetailList.get(j).getOrderdetailnumber());//该产品项目的总价
							orderpricetotalmoney += priceList.get(0).getOrderpricemoney() * orderDetailList.get(j).getOrderdetailnumber();
						}
					}
					orderTableList.get(i).setOrderpriceservicetotalmoney(orderpricetotalmoney);//维修工自己的总价
					OTAODL.setOrderTableAndBuildingType(orderTableList.get(i)); // 添加维修单表
					OTAODL.setOrderDetailList(orderDetailList); // 添加维修单详情表
					
					OTAODLList.add(OTAODL); // 为List添加数据
				}
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return OTAODLList;
	}
	
	/**
	 * 在维修工竞标时如果本人竞标过显示曾经竞标的钱
	 * 
	 * 根据OrderTableAndBuildingType实体联表查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
   	 * 查询数量(OrderTableAndBuildingType的数量)
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/10/18
	 */
	public int selectThreeTablesCountByUnionDataAndSnatchMoney(OrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectTwoTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesCountByUnionDataAndSnatchMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 在维修工竞标时如果本人竞标过显示曾经竞标的钱
	 * 
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/10/18
	 */
	public List<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablestByUnionDataAndSnatchMoney(OrderTableAndBuildingType unionData) {
		// 返回数据List
		List<OrderTableAndBuildingTypeAndOrderDetailList> OTAODLList = new ArrayList<OrderTableAndBuildingTypeAndOrderDetailList>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			
			// 返回数据
			OrderTableAndBuildingTypeAndOrderDetailList OTAODL = new OrderTableAndBuildingTypeAndOrderDetailList();

			// 查询维修单表
			List<OrderTableAndBuildingType> orderTableList = new ArrayList<OrderTableAndBuildingType>();
			orderTableList = orderTableDao.selectTwoTablesByUnionData(session, unionData);
			
			if (orderTableList.size() == 0) {
				OTAODL.setOrderTableAndBuildingType(null); // 添加维修单表
				OTAODL.setOrderDetailList(null); // 添加维修单详情表
				
				OTAODLList.add(OTAODL); // 为List添加数据
			} else {
				// 循环查询到的维修单表，根据维修单ID，查询维修单详情表
				for (int i = 0; i < orderTableList.size(); i++) {
					
					double orderpricetotalmoney = 0;//维修工显示的总价
					// 根据维修单ID，查询维修单详情表
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrderid(orderTableList.get(i).getOrderid());
					List<OrderDetail> orderDetailList = null;
					orderDetailList = orderDetailDao.selectByOrderDetail(session, orderDetail); // 查询
					//通过当前竞标人、产品ID、项目ID、查询维修工自己设置的单价
					for  (int j = 0; j < orderDetailList.size(); j++) {
						OrderPriceAndGoodsAndProjectAndUser orderPriceAndGoodsAndProjectAndUser = new OrderPriceAndGoodsAndProjectAndUser();
						orderPriceAndGoodsAndProjectAndUser.setOrderid(orderTableList.get(i).getOrderid());//订单ID
						orderPriceAndGoodsAndProjectAndUser.setGoodsid(orderDetailList.get(j).getGoodsid());//产品ID
						orderPriceAndGoodsAndProjectAndUser.setProjectid(orderDetailList.get(j).getProjectid());//项目ID
						orderPriceAndGoodsAndProjectAndUser.setOrderpriceupdateuserid(unionData.getCurrentuserid());//当前操作人
						List<OrderPriceAndGoodsAndProjectAndUser> priceList = orderPriceDao.selectFourTablesByUnionData(session, orderPriceAndGoodsAndProjectAndUser); // 查询数据
						double money = 0;
						if (priceList.size() == 0) {
							orderDetailList.get(j).setOrderpricemoney(money);
						} else {
							orderDetailList.get(j).setOrderpricemoney(priceList.get(0).getOrderpricemoney());//维修工自己维护的单价
							orderDetailList.get(j).setOrderdetailpricetotalmoney(priceList.get(0).getOrderpricemoney() * orderDetailList.get(j).getOrderdetailnumber());//该产品项目的总价
							orderpricetotalmoney += priceList.get(0).getOrderpricemoney() * orderDetailList.get(j).getOrderdetailnumber();
						}
					}
					SnatchAndOrderTableAndUser snatchAndOrderTableAndUser = new SnatchAndOrderTableAndUser();
					snatchAndOrderTableAndUser.setPagenumber(-1);//不分页
					snatchAndOrderTableAndUser.setOrderid(orderTableList.get(i).getOrderid());//订单id
					snatchAndOrderTableAndUser.setServiceuserid(unionData.getCurrentuserid());//当前操作人，维修工
					List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();
					snatchList = snatchDao.selectThreeTablesByUnionData(session, snatchAndOrderTableAndUser);
					
					orderTableList.get(i).setOrdertraveltotalmoney(snatchList.get(0).getSnatchtravelmoney());//维修工自己的差旅费
					orderTableList.get(i).setOrderdaymoney(snatchList.get(0).getSnatchdaymoney());//维修工日工资
					orderTableList.get(i).setOrderday(snatchList.get(0).getSnatchday());//维修工天数
					orderTableList.get(i).setOrderpeoplenumber(snatchList.get(0).getSnatchpeoplenumber());//维修工人数
					orderTableList.get(i).setOrdermaterialmoney(snatchList.get(0).getSnatchmaterialmoney());//维修工自己的材料费
					if (orderTableList.get(i).getOrderprojecttype().equals("WX")) {
						orderTableList.get(i).setOrderpriceservicetotalmoney(snatchList.get(0).getSnatchmoney() +snatchList.get(0).getSnatchtravelmoney());//维修工自己的总价 + 差旅费
					}else if (orderTableList.get(i).getOrderprojecttype().equals("AZ")){
						orderTableList.get(i).setOrderpriceservicetotalmoney(orderpricetotalmoney +snatchList.get(0).getSnatchtravelmoney());//维修工自己的总价 + 差旅费
					}

					OTAODL.setOrderTableAndBuildingType(orderTableList.get(i)); // 添加维修单表
					OTAODL.setOrderDetailList(orderDetailList); // 添加维修单详情表
					
					OTAODLList.add(OTAODL); // 为List添加数据
				}
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablestByUnionDataAndSnatchMoney--error:" + e.getMessage());
		}

		return OTAODLList;
	}

	/**
	 * 维修人员评价客户时用到
	 * 
	 * 根据OrderTable实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/10/11
	 */
	public int selectEvaluateCustomerCountByOrderTable(OrderTable orderTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectEvaluateCustomerCountByOrderTable(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectEvaluateCustomerCountByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 维修人员评价客户时用到
	 * 
	 * 根据OrderTable实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/10/11
	 */
	public List<OrderTable> selectEvaluateCustomerByOrderTable(OrderTable orderTable) {
		List<OrderTable> orderTableList = new ArrayList<OrderTable>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderTableList = orderTableDao.selectEvaluateCustomerByOrderTable(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectEvaluateCustomerByOrderTable--error:" + e.getMessage());
		}

		return orderTableList;
	}
	
	/**
	 * 客户评价维修人员时用到
	 * 
	 * 根据OrderTable实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/10/11
	 */
	public int selectEvaluateServiceCountByOrderTable(OrderTable orderTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectEvaluateServiceCountByOrderTable(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectEvaluateServiceCountByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 客户评价维修人员时用到
	 * 
	 * 根据OrderTable实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/10/11
	 */
	public List<OrderTable> selectEvaluateServiceByOrderTable(OrderTable orderTable) {
		List<OrderTable> orderTableList = new ArrayList<OrderTable>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderTableList = orderTableDao.selectEvaluateServiceByOrderTable(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectEvaluateServiceByOrderTable--error:" + e.getMessage());
		}

		return orderTableList;
	}
	
	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;
		int deleteDetailNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				//通过ID 查询出来的orderTable
				OrderTable orderTable = orderTableDao.selectByPrimaryKey(session, list.get(i));

				deleteNum = deleteNum + orderTableDao.deleteByPrimaryKey(session, list.get(i));

				//删除OrderDetail 中的数据
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderid(orderTable.getOrderid());
				deleteDetailNum = deleteDetailNum + orderDetailDao.deleteByOrderDetail(session, orderDetail);
			}

			if (deleteNum ==0 || deleteDetailNum == 0){
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				OrderTable order = new OrderTable();
				order.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + orderTableDao.deleteByOrderTable(session, order); 
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

	/**
	 * 根据OrderTable实体更新平台确认收款状态
	 *
	 * 更新平台收入金额明细表
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/10/23
	 */
	public int updateByOrderTableAndBackMoney(OrderTable orderTable, BackMoney backMoney, UserPayment userPayment) {
		int num = 0;
		int backMoneyNum = 0;
		int userPaymentNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.updateByOrderTable(session, orderTable);
			backMoneyNum = backMoneyDao.insertByBackMoney(session, backMoney);
			userPaymentNum = userPaymentDao.updateByUserPayment(session, userPayment);

			if (num == 0 || backMoneyNum == 0 || userPaymentNum == 0){
				return 0;
			}
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--updateByOrderTableAndBackMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体更新维修人员确认收款状态
	 *
	 * 更新平台收入金额明细表
	 *
	 * 添加维修人员收支金额明细表记录
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/10/23
	 */
	public int updateByOrderTableAndServiceMoney(OrderTable orderTable, BackMoney backMoney, UserServiceIncomeMoney serviceIncomeMoney, UserPayment userPayment) {
		int num = 0;
		int backMoneyNum = 0;
		int serviceIncomeMoenyNum = 0;
		int updateUserPaymentNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.updateByOrderTable(session, orderTable);
			backMoneyNum = backMoneyDao.insertByBackMoney(session, backMoney);
			serviceIncomeMoenyNum = userServiceIncomeMoneyDao.insertByUserServiceIncomeMoney(session, serviceIncomeMoney);
			updateUserPaymentNum = userPaymentDao.updateByUserPayment(session, userPayment);

			if (num == 0 || backMoneyNum == 0|| serviceIncomeMoenyNum == 0|| updateUserPaymentNum ==0){
				return 0;
			}
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--updateByOrderTableAndServiceMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体更新维修人员确认收款状态
	 *
	 * 更新平台收入金额明细表
	 *
	 * 添加维修人员收支金额明细表记录
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/10/23
	 */
	public int updateByOrderTableAndBackMoneyAndServiceMoney(OrderTable orderTable, BackMoney backMoney, UserServiceIncomeMoney serviceIncomeMoney, User updateUser, UserPayment userPayment) {
		int num = 0;
		int backMoneyNum = 0;
		int serviceIncomeMoenyNum = 0;
		int updateUserNum = 0;
		int updateUserPaymentNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.updateByOrderTable(session, orderTable);
			backMoneyNum = backMoneyDao.insertByBackMoney(session, backMoney);
			serviceIncomeMoenyNum = userServiceIncomeMoneyDao.insertByUserServiceIncomeMoney(session, serviceIncomeMoney);
			updateUserNum = userDao.updateByUser(session, updateUser);
			updateUserPaymentNum = userPaymentDao.updateByUserPayment(session, userPayment);

			if (num == 0 || backMoneyNum == 0|| serviceIncomeMoenyNum == 0 || updateUserNum == 0 ||updateUserPaymentNum ==0){
				return 0;
			}
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--updateByOrderTableAndServiceMoney--error:" + e.getMessage());
		}

		return num;
	}


	/**
	 * 根据News实体联表模糊查询(用户报表中任务清单次数详情)
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/09/19
	 */
	public int selectFourTablesCountDetailRport(OrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectFourTablesCountDetailRport(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("NewsService--selectFourTablesCountDetailRport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTableAndBuildingType实体联表模糊查询 (用户报表中任务清单次数详情)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/09/19
	 */
	public List<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> selectFourTablesDetailRport(OrderTableAndBuildingType unionData) {
		List<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> orderDetailList = new ArrayList<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderDetailList = orderTableDao.selectFourTablesDetailRport(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("NewsService--selectFourTablesDetailRport--error:" + e.getMessage());
		}

		return orderDetailList;
	}

	/**
	 * 根据OrderTableAndBuildingType实体查询
	 *
	 * 查询数量
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/12/26
	 */
	public int selectThreeTablesCountByUnionDataProfitTotalMoneyReport(OrderTableAndBuildingType orderTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderTableDao.selectTwoTablesCountByUnionData(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesCountByUnionDataProfitTotalMoneyReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTableAndBuildingType实体查询
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/12/26
	 */
	public List<OrderTableAndBuildingType> selectThreeTablesByUnionDataProfitTotalMoneyReport(OrderTableAndBuildingType orderTable) {
		List<OrderTableAndBuildingType> orderTableList = new ArrayList<OrderTableAndBuildingType>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderTableList = orderTableDao.selectTwoTablesByUnionData(session, orderTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderTableService--selectThreeTablesByUnionDataProfitTotalMoneyReport--error:" + e.getMessage());
		}

		return orderTableList;
	}


}
