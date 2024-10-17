package designpattern.bookrental.model.member;

import designpattern.bookrental.model.enums.MemberLevel;
import java.time.LocalDate;

public class Member {

  private String memberId;
  private String memberName;
  private MemberLevel memberLevel;
  private LocalDate creationDate;

  public Member(String memberId, String memberName, MemberLevel memberLevel){
    this.memberId = memberId;
    this.memberName = memberName;
    this.memberLevel = memberLevel;
    this.creationDate = LocalDate.now();
  }

  public String getMemberId() {
    return memberId;
  }

  public String getMemberName() {
    return memberName;
  }

  public MemberLevel getMemberLevel() {
    return memberLevel;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }
}
