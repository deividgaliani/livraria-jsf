package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent evento) {
		FacesContext context = evento.getFacesContext();
		//captura o nome da pagina acessada
		String nomePagina = context.getViewRoot().getViewId();
		
		//verifica se é a pagina de login
		if("/login.xhtml".equals(nomePagina)) {
			return;
		}
		
		//captura o usuario e verifica se esta logado
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");		
		if(usuarioLogado != null) {
			return;
		}
		
		//redireciona para a pagina login.xhtml
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login.xhtml?faces-redirect=true");
		context.renderResponse();		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		//doNothing
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
