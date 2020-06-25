package com.mrfahim.ecommapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.mrfahim.ecommapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.mrfahim.ecommapp.service..")
            .or()
            .resideInAnyPackage("com.mrfahim.ecommapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.mrfahim.ecommapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
