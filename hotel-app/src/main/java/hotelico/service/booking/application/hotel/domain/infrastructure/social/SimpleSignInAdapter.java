package hotelico.service.booking.application.hotel.domain.infrastructure.social;///*
// * Copyright 2014 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package de.hotelico.social;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import eu.getsoftware.hotelico.model.Customer;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.web.SignInAdapter;
//import org.springframework.web.context.request.NativeWebRequest;
//
///**
// * Signs the user in by setting the currentUser property on the {@link SecurityContext}.
// * Remembers the sign-in after the current request completes by storing the user's id in a cookie.
// * This is cookie is read in {@link CustomerInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)} on subsequent requests.
// * @author Keith Donald
// * @see CustomerInterceptor
// */
//public final class SimpleSignInAdapter implements SignInAdapter {
//
//	private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();
//	
//	public String signIn(String linkedInId, Connection<?> connection, NativeWebRequest request) {
//		SecurityContext.setCurrentCustomer(new Customer());
//		userCookieGenerator.addCookie(linkedInId, request.getNativeResponse(HttpServletResponse.class));
//		return null;
//	}
//
//}
