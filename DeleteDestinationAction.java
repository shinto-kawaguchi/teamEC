package com.internousdev.red.action;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.red.dao.DestinationInfoDAO;
import com.internousdev.red.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteDestinationAction extends ActionSupport implements SessionAware{

	private DestinationInfoDAO dao = new DestinationInfoDAO();
	public Map<String, Object> session;
	private int id;
	private ArrayList<DestinationInfoDTO> destinationInfoList = new ArrayList<DestinationInfoDTO>();

	public String execute(){

		// セッションタイムアウトの判定
		if(Objects.isNull(session.get("userId")) && Objects.isNull(session.get("tempUserId"))){
			return "sessionTimeout";
		}

		String result = ERROR;

		// ユーザーIDの取得
		String userId = String.valueOf(session.get("userId"));

		//宛先情報IDに紐付く宛先情報の削除。
		int count = dao.deleteDestinationInfo(id, userId);

		if(count > 0){
			//宛先情報削除後の宛先情報リストの再取得。
			destinationInfoList = dao.selectDestinationInfo(String.valueOf(session.get("userId")));
			return SUCCESS;
		}
		return result;
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	public ArrayList<DestinationInfoDTO> getDestinationInfoList(){
		return this.destinationInfoList;
	}
}