package com.businesscard.domain.IService;

import java.util.List;

import com.businesscard.domain.Emtity.BusinessCardInfo;

public interface IBusinessCardInfo {

	public List<BusinessCardInfo> findBusinessCard();
	public List<BusinessCardInfo> findBusinessCardByUserId(String userId);
	public boolean AddBusinessCard(BusinessCardInfo businessCardInfo);
}
