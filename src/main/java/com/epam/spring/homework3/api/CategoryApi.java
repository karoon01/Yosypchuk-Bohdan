package com.epam.spring.homework3.api;

import com.epam.spring.homework3.model.DTO.CategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Activity category management API")
@RequestMapping("/api/v1/activity/category")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public interface CategoryApi {

    @ApiOperation("Get all activity categories")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    List<CategoryDTO> getAllActivityCategories();

    @ApiOperation("Get activity category by name")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{name}")
    CategoryDTO getActivityCategory(@PathVariable String name);

    @ApiOperation("Create activity category")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO);

    @ApiOperation("Delete activity category by id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    ResponseEntity<Void> removeCategory(@PathVariable Long id);
}
