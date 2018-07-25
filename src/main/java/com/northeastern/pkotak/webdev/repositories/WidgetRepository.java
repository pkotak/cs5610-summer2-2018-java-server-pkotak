package com.northeastern.pkotak.webdev.repositories;

import com.northeastern.pkotak.webdev.models.Widget;
import org.springframework.data.repository.CrudRepository;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
}
