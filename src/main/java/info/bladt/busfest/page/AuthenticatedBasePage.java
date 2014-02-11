package info.bladt.busfest.page;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@AuthorizeInstantiation("ADMIN")
public class AuthenticatedBasePage extends BasePage {
}
