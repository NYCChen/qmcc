package com.qmcc.sys.common;

import java.util.List;

import com.qmcc.sys.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LJH
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser {

	private User user;

	private List<String> roles;

	private List<String> permissions;
}
