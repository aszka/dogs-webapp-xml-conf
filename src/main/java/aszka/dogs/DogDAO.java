package aszka.dogs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
class DogDAO {

	private final Logger logger = LoggerFactory.getLogger(DogDAO.class);
	private int id = 0;

	List<Dog> dogs = new ArrayList<>();

	public void addDog(Dog dog) throws IllegalStateException {
		dog.setId(++id);
		dogs.add(dog);
		logger.info("Add a new dog: {}", dog);
	}

	public List<Dog> dogs() {
		return dogs;
	}

	public Optional<Dog> get(int id) {
		return dogs.stream().filter(dog -> dog.getId() == id).findFirst();
	}

	public void edit(Dog dog) {
		dogs.stream().forEach(d -> {
			if (d.getId() == dog.getId()) {
				d.setName(dog.getName());
				d.setBirthDate(dog.getBirthDate());
				d.setOwnerName(dog.getOwnerName());
			}
		});
	}

	public void delete(int id) {
		if (this.get(id).isPresent()) {
			dogs.remove(dogs.stream().filter(d -> d.getId() == id).findAny().get());
		}
	}
}
