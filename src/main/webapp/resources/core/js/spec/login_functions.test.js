/**
JASMINE DOCUMENTATION: https://jasmine.github.io/api/5.8/global
**/
import { validateLoginFields } from "../login_functions.js";

describe("Login Functions", function() {
  it("should return true when email and password are valid", function() {
    expect(validateLoginFields("test@unlam.edu.ar", "test")).toBe(true);
  });

  it("should return false when email is 'test@unlam.edu.ar' and password is empty", function() {
    expect(validateLoginFields("test@unlam.edu.ar", "")).toBe(false);
  });

  it("should return false when email is empty and password is 'test'", function() {
    expect(validateLoginFields("", "test")).toBe(false);
  });

  it("should return false when email is empty and password is empty", function() {
    expect(validateLoginFields("", "")).toBe(false);
  });

  it("should return false when email is null and password is null", function() {
    expect(validateLoginFields(null, null)).toBe(false);
  });

  it("should return false when email is null and password is 'test'", function() {
    expect(validateLoginFields(null, "test")).toBe(false);
  });

  it("should return false when email is 'test@unlam.edu.ar' and password is null", function() {
    expect(validateLoginFields("test@unlam.edu.ar", null)).toBe(false);
  });

  it("should return false when email is empty and password is null", function() {
    expect(validateLoginFields("", null)).toBe(false);
  });  
});