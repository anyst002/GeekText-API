package geektext;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "credit_card", uniqueConstraints = 
@UniqueConstraint(columnNames = {"id"}))
public class CreditCard {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Nonnull @Id Integer id;
	private @Nonnull Integer userId;
	private @Nonnull Long cardNum;
	private @Nonnull String cvv;
	@JsonFormat(pattern = "yyyy-MM")
	private @Nonnull Date expDate;
	private @Nonnull String cardholder;
	
	//======================
	//=== Getter methods ===
	//======================
	
	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}
	
	public Long getCardNum() {
		return cardNum;
	}
	
	public String getCVV() {
		return cvv;
	}
	
	public Date getExpDate() {
		return expDate;
	}
	
	public String getCardholder() {
		return cardholder;
	}

	//======================
	//=== Setter methods ===
	//======================
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setCardNum(Long cardNum) {
		this.cardNum = cardNum;
	}
	
	public void setCVV(String cvv) {
		this.cvv = cvv;
	}
	
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
}