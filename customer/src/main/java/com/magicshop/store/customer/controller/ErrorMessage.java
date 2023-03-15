package com.magicshop.store.customer.controller;

import java.util.List;
import java.util.Map;

import com.magicshop.store.customer.controller.ErrorMessage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ErrorMessage {
	private String code;
	private List<Map<String,String>> messages;

}
