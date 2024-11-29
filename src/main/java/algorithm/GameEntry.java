package com.algorithms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class GameEntry {

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private int score;
}
