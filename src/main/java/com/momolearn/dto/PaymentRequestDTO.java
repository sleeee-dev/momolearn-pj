package com.momolearn.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PaymentRequestDTO {
	
	private List<String> checkedTitles;
	
	private String memId;

}
