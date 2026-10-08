package ru.arkhipov.MyFirstTestAppSpringBoot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	private ArrayList<String> arrayList = new ArrayList<>();
	private HashMap<Integer, String> hashMap = new HashMap<>();
	private int mapKeyCounter = 0;

	@GetMapping("/update-array")
	public String updateArrayList(@RequestParam(value = "s", defaultValue = "") String s) {
		if (arrayList == null) {
			arrayList = new ArrayList<>();
		}
		arrayList.add(s);
		return "Добавлено в ArrayList: " + s;
	}

	@GetMapping("/show-array")
	public String showArrayList() {
		return "ArrayList: " + arrayList.toString();
	}

	@GetMapping("/update-map")
	public String updateHashMap(@RequestParam(value = "s", defaultValue = "") String s) {
		if (hashMap == null) {
			hashMap = new HashMap<>();
		}
		mapKeyCounter++;
		hashMap.put(mapKeyCounter, s);
		return "Добавлено в HashMap: " + s;
	}

	@GetMapping("/show-map")
	public String showHashMap() {
		StringBuilder sb = new StringBuilder("HashMap: {");
		boolean f = true;
		for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
			if (!f) sb.append(", ");
			sb.append(entry.getKey()).append("=").append(entry.getValue());
			f = false;
		}
		sb.append("}");
		return sb.toString();
	}

	@GetMapping("/show-all-length")
	public String showAllLength() {
		return String.format("количество элементов в ArrayList: %d; и HashMap: %d", arrayList.size(), hashMap.size());
	}
}
