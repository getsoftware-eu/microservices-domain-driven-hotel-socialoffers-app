/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.social;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.CookieGenerator;

/**
 * Utility class for managing the quick_start user cookie that remembers the signed-in user.
 * @author Keith Donald
 */
public final class UserCookieGenerator {

	private final CookieGenerator userCookieGenerator = new CookieGenerator();

	public UserCookieGenerator() {
		userCookieGenerator.setCookieName("hotelico_user");
	}

	public void addCookie(String userId, HttpServletResponse response) {
		userCookieGenerator.addCookie(response, userId);
	}
	
	public void removeCookie(HttpServletResponse response) {
		userCookieGenerator.addCookie(response, "");
	}
	
	public String readCookieValue(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(userCookieGenerator.getCookieName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
