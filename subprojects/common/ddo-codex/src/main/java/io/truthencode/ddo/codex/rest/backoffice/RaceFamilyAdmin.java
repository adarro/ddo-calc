package io.truthencode.ddo.codex.rest.backoffice;


import io.quarkiverse.renarde.backoffice.BackofficeController;
import io.quarkus.security.Authenticated;
import io.truthencode.ddo.codex.model.RaceFamily;


@Authenticated
public class RaceFamilyAdmin extends BackofficeController<RaceFamily> {

}