package aszka.dogs;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

class Dog {

	public static final String PATTERN_NAME = "[a-zA-Z]*( {1}[a-zA-Z]*)*";

	@NotNull
	private int id;

	@NotBlank
	@Pattern(regexp = PATTERN_NAME, message = "{dog.name.pattern}")
	@Size(min = 2, max = 25, message = "{dog.name.size}")
	private String name;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate birthDate;

	@NotBlank
	@Pattern(regexp = PATTERN_NAME, message = "{dog.name.pattern}")
	@Size(min = 2, max = 25, message = "{dog.name.size}")
	private String ownerName;

	public Dog() {
	}

	public Dog(int id, String name, LocalDate birthDate, String ownerName) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.ownerName = ownerName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String intruduceYourself() {
		return "Hello, I'm " + name + ", I was born " + birthDate + ", my owner is " + ownerName + "\n";
	}

}
