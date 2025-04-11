package io.pipecrafts.core.io.pipecrafts.core.fleet.bus;

import io.pipecrafts.commons.core.flt.bus.Bus;
import io.pipecrafts.commons.core.flt.bus.BusType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BusValidationTests {

  private static Validator validator;

  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void whenAllFieldsAreCorrectThenNoExceptionThrown() {
    var bus = new Bus(1L, BusType.FIRST_CLASS, "ABC1234", "6324");
    Set<ConstraintViolation<Bus>> violations = validator.validate(bus);
    assertThat(violations).isEmpty();
  }

  @Test
  public void whenPlateNumberIsDefinedButInvalidThenValidationFails() {
    var bus = new Bus(1L, BusType.FIRST_CLASS, "AB123", "6324");
    Set<ConstraintViolation<Bus>> violations = validator.validate(bus);

    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage()).isEqualToIgnoringCase("Invalid plate number length");
  }
}
