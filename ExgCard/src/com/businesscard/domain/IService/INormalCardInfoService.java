package com.businesscard.domain.IService;

import java.util.List;

import com.businesscard.domain.Emtity.NormalCardInfo;

public interface INormalCardInfoService {
	   public NormalCardInfo findMyCardByUserPhone(String userId);
	    public List<NormalCardInfo> findNormalCardsByUserPhone(String userId);
	    public boolean addMyCard(NormalCardInfo normalCardInfo);
	    public boolean addNormalCardByUserPhone(String userNameFirstAlpha,NormalCardInfo normalCardInfo);
	    public boolean alterMyCard(NormalCardInfo normalCardInfo);
	    public boolean alterNormalCard(NormalCardInfo normalCardInf,String userId,String aplao);
	    public boolean delNormalCardByUserPhone(String userPhone);
	    public NormalCardInfo findNormalCardByUserPhone(String userId);
}
