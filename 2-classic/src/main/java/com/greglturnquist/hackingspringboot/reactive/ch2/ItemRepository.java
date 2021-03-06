/*
 * Copyright 2019 the original author or authors.
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
package com.greglturnquist.hackingspringboot.reactive.ch2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

/**
 * @author Greg Turnquist
 */
@Service
class ItemRepository {

	private static AtomicLong ITEM_SEQ = new AtomicLong(0);
	private static List<Item> ITEMS = new ArrayList<>();

	Item save(Item item) {
		item.setId(ITEM_SEQ.getAndIncrement());
		ITEMS.add(item);
		return item;
	}

	List<Item> findAll() {
		return ITEMS;
	}

	void deleteById(long id) {
		findAll().stream()
			.filter(item -> item.getId() == id)
			.findFirst()
			.ifPresent(item -> ITEMS.remove(item));
	}
}
