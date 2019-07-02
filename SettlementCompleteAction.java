package com.internousdev.red.action;

import src.com.internousdev.red.dao.CartInfoDAO;
import src.com.internousdev.red.dao.DestinationInfoDAO;
import src.com.internousdev.red.dao.PurchaseHistoryInfoDAO;
import src.com.internousdev.red.dto.CartInfoDTO;
import src.com.internousdev.red.dto.DestinationInfoDTO;

public class SettlementCompleteAction extends ActionSupport implements SessionAware{

	private String id;
	public Map<String, Object> session;
	private List<CartInfoDTO> cartInfoDTOList;
	private ArrayList<DestinationInfoDTO> destinationInfoList;
	private boolean idMatch;

	//cartInfoDTOList = (List<CartInfoDTO>) session.get("cartInfoDTOList");部分のキャスト時の警告を非表示にしている。
	@SuppressWarnings("unchecked")
	public String execute(){

		// セッションタイムアウトの判定
		if(Objects.isNull(session.get("userId")) && Objects.isNull(session.get("tempUserId"))){
			return "sessionTimeout";
		}

		String result = ERROR;

		String userId = String.valueOf(session.get("userId"));
		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		cartInfoDTOList = (List<CartInfoDTO>) session.get("cartInfoDTOList");
		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();

		//宛先情報の確認
		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
		destinationInfoList = destinationInfoDAO.selectDestinationInfo(userId);

		for(DestinationInfoDTO dto: destinationInfoList){
			if(id.equals(String.valueOf(dto.getId()))){
				idMatch = true;
			}
		}

		if(idMatch){
			int count = 0;
			for(CartInfoDTO dto : cartInfoDTOList){
				count += purchaseHistoryInfoDAO.regist(
						userId,
						dto.getProductId(),
						dto.getProductCount(),
						Integer.parseInt(id),
						dto.getPrice()
						);
			}

			if(count > 0){
				count = cartInfoDAO.deleteAll(userId);
				result = SUCCESS;
			}
		}
		return result;
	}

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

	public ArrayList<DestinationInfoDTO> getDestinationInfoList() {
		return destinationInfoList;
	}

	public void setDestinationInfoList(ArrayList<DestinationInfoDTO> destinationInfoList) {
		this.destinationInfoList = destinationInfoList;
	}

}
