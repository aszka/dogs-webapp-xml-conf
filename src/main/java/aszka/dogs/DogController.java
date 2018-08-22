package aszka.dogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/dogs")
public class DogController {

	public static final String TEMPLATES_ADD_EDIT = "templates/addEdit";
	public static final String TEMPLATES_LIST = "templates/list";
	public static final String DOGS = "dogs";
	public static final String SAVE = "save";
	public static final String CANCEL = "cancel";
	public static final String REDIRECT_DOGS_LIST = "redirect:/dogs/list/";

	@Autowired
	private DogDAO dogDao;

	@RequestMapping("/")
	public String home(Model model) {
		return "templates/index";
	}

	@RequestMapping("/list/")
	public String getList(Model model) {
		model.addAttribute(DOGS, dogDao.dogs());
		return TEMPLATES_LIST;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute Dog dog) {
		return TEMPLATES_ADD_EDIT;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, params = SAVE)
	public String add(@Valid Dog dog, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TEMPLATES_ADD_EDIT;
		}
		dogDao.addDog(dog);
		return REDIRECT_DOGS_LIST;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, params = CANCEL)
	public String add(Model model) {
		model.addAttribute(DOGS, dogDao.dogs());
		return TEMPLATES_LIST;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model model) {
		Optional<Dog> dog = dogDao.get(id);
		if(dog.isPresent()) {
			model.addAttribute("dog", dog.get());
			return TEMPLATES_ADD_EDIT;
		} else {
			model.addAttribute("dogs", dogDao.dogs());
			return TEMPLATES_LIST;
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, params = SAVE)
	public String edit(@Valid Dog dog, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return TEMPLATES_ADD_EDIT;
		}

		dogDao.edit(dog);
		model.addAttribute(DOGS, dogDao.dogs());
		return TEMPLATES_LIST;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, params = CANCEL)
	public String edit(Model model) {
		model.addAttribute(DOGS, dogDao.dogs());
		return TEMPLATES_LIST;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
		dogDao.delete(id);
		redirectAttributes.addFlashAttribute("info", "The dog with id=" + id + " has been deleted");
		return REDIRECT_DOGS_LIST;
	}
}
