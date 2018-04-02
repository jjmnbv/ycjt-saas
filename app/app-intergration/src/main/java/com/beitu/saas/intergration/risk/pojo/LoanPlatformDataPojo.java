package com.beitu.saas.intergration.risk.pojo;

import java.util.List;

public class LoanPlatformDataPojo {
    
    private LoanPlatformBasicInfoPojo basicInfo;
    
    private List<LoanPlatformBankCardPojo> bankCards;
    
    private LoanPlatformBorrowedPojo borrowed;
    
    private LoanPlatformLendPojo lend;
    
    private List<LoanPlatformFriendPojo> friends;
    
    public LoanPlatformBasicInfoPojo getBasicInfo() {
        return basicInfo;
    }
    
    public List<LoanPlatformBankCardPojo> getBankCards() {
        return bankCards;
    }
    
    public LoanPlatformBorrowedPojo getBorrowed() {
        return borrowed;
    }
    
    public LoanPlatformLendPojo getLend() {
        return lend;
    }
    
    public List<LoanPlatformFriendPojo> getFriends() {
        return friends;
    }
}
