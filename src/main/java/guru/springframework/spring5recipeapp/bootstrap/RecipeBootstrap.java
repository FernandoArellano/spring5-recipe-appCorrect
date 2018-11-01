package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashSpoonUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintSpoonUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsSpoonUomOptional = unitOfMeasureRepository.findByDescription("Cups");

        if(!cupsSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //getOptionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom= dashSpoonUomOptional.get();
        UnitOfMeasure pintUom= pintSpoonUomOptional.get();
        UnitOfMeasure cupsUom = cupsSpoonUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Take avocado and smash it.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("notes guac");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(5), teaUom));
        guacRecipe.addIngredient(new Ingredient("lemon", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("chiles", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("tomato", new BigDecimal(5), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        //Yummy Guac
        Recipe tacosRecipe = new Recipe();
        tacosRecipe .setDescription("Tacos");
        tacosRecipe .setPrepTime(20);
        tacosRecipe .setCookTime(10);
        tacosRecipe .setDifficulty(Difficulty.MODERATE);
        tacosRecipe .setDirections("Take meat put it on the taco");

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("notes tacos");

        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.addIngredient(new Ingredient("chilli", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredient("oregano", new BigDecimal(5), teaUom));
        tacosRecipe.addIngredient(new Ingredient("cumin", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("salt", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredient("orange", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("orange juice", new BigDecimal(2), dashUom));
        tacosRecipe.addIngredient(new Ingredient("chicken", new BigDecimal(5), eachUom));
        tacosRecipe.addIngredient(new Ingredient("tortillas", new BigDecimal(5), eachUom));
        tacosRecipe.addIngredient(new Ingredient("aragula", new BigDecimal(5), eachUom));
        tacosRecipe.addIngredient(new Ingredient("avocados", new BigDecimal(5), eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes", new BigDecimal(5), eachUom));
        tacosRecipe.addIngredient(new Ingredient("tomates", new BigDecimal(5), eachUom));
        tacosRecipe.addIngredient(new Ingredient("onion", new BigDecimal(5), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cilantro", new BigDecimal(5), eachUom));
        tacosRecipe.addIngredient(new Ingredient("sour cream", new BigDecimal(5), eachUom));
        tacosRecipe.addIngredient(new Ingredient("lime", new BigDecimal(5), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;
    }


}


