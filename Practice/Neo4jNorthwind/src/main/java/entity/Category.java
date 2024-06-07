package entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6479744029830732683L;
	@SerializedName("categoryID")
	private String id;
	@SerializedName("categoryName")
	private String name;
	private String description;
	
}
