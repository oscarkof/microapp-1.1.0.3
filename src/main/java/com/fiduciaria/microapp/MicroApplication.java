package com.fiduciaria.microapp;

import com.fiduciaria.microapp.auth.BasicAuthenticationSession;
import com.fiduciaria.microapp.auth.page.MicroAppSignInPage;
import com.fiduciaria.microapp.base.BasePage;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.page.InternalErrorPage;
import com.fiduciaria.microapp.page.MainPage;
import com.fiduciaria.microapp.page.error.Error403;
import com.fiduciaria.microapp.page.error.Error404;
import com.fiduciaria.microapp.page.statefull.administracion.AdimistracionTenant;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.GestionActividades;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.GestionSolicitudes;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.GestionUsuario;
import com.fiduciaria.microapp.page.stateless.usuarios.preingreso.NotificacionEnvioSolicitud;
import com.fiduciaria.microapp.page.stateless.usuarios.preingreso.PreIngresoDatosUsuario;
import java.util.Base64;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.core.request.mapper.CryptoMapper;
import org.apache.wicket.core.request.mapper.IMapperContext;
import org.apache.wicket.devutils.stateless.StatelessChecker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.CsrfPreventionRequestCycleListener;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestMapper;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.settings.ExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.lang.Bytes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MicroApplication extends AuthenticatedWebApplication {
  public void init() {
    super.init();
    System.out.println("Ejecuciingreso aplicacion!!");
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.scan(new String[] { "com.fiduciaria.microapp.beans" });
    ctx.refresh();
    getComponentInstantiationListeners().add(new SpringComponentInjector((WebApplication)this, (ApplicationContext)ctx));
    initCheckers();
    getApplicationSettings().setPageExpiredErrorPage(MainPage.class);
    getApplicationSettings().setAccessDeniedPage(Error403.class);
    getApplicationSettings().setInternalErrorPage(InternalErrorPage.class);
    getApplicationSettings().setUploadProgressUpdatesEnabled(true);
    Locale.setDefault(Locale.forLanguageTag("es-CO"));
    getDebugSettings().setDevelopmentUtilitiesEnabled(false);
    getMarkupSettings().setStripWicketTags(true);
    getMarkupSettings().setCompressWhitespace(true);
    getMarkupSettings().setStripComments(true);
    getExceptionSettings().setAjaxErrorHandlingStrategy(ExceptionSettings.AjaxErrorStrategy.REDIRECT_TO_ERROR_PAGE);
    getExceptionSettings().setUnexpectedExceptionDisplay(ExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
    getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
    setRootRequestMapper((IRequestMapper)new CryptoMapper(getRootRequestMapper(), (Application)this));
    getRequestCycleListeners().add((new CsrfPreventionRequestCycleListener())
        .setErrorCode(403)
        
        .setConflictingOriginAction(CsrfPreventionRequestCycleListener.CsrfAction.ALLOW)
        .setNoOriginAction(CsrfPreventionRequestCycleListener.CsrfAction.ALLOW));
    getResourceSettings().setThrowExceptionOnMissingResource(false);
    getDebugSettings().setAjaxDebugModeEnabled(false);
    getStoreSettings().setMaxSizePerSession(Bytes.kilobytes(50000L));
    mountPackage("/error", Error404.class);
    mountPage("/inicio", MainPage.class);
    mountPage("/recursos", BasePage.class);
    mountPage("/internal", InternalErrorPage.class);
    mountPage("/autogestion/administracion", AdimistracionTenant.class);
    mountPackage("/autogestion/solicitudes", GestionSolicitudes.class);
    mountPackage("/autogestion/actividades", GestionActividades.class);
    mountPackage("/autogestion/usuarios", GestionUsuario.class);
    mountPackage("/autogestion/usuarios/notificacion", NotificacionEnvioSolicitud.class);
    mountPage("/usuarios/preingreso", PreIngresoDatosUsuario.class);
    getRequestCycleListeners().add(new IRequestCycleListener() {
          public void onEndRequest(RequestCycle cycle) {
            WebResponse response = (WebResponse)cycle.getResponse();
            response.setHeader("X-XSS-Protection", "1; mode=block");
            response.setHeader("X-Frame-Options", "sameorigin");
            response.setHeader("X-Content-Type-Options", "nosniff");
            response.disableCaching();
            response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, private");
            response.setHeader("Pragma", "no-cache");
          }
        });
    initAuthStrategy();
    initAppIdentification();
  }
  
  private void initAppIdentification() {
    ResourceBundle resources = ResourceBundle.getBundle("aplicationbase");
    String keyParam = resources.getString("com.bbva.app.identification");
    AppConfParamConstant.setKeyEncry(Base64.getDecoder().decode(keyParam));
  }
  
  private void initAuthStrategy() {
    getSecuritySettings().setAuthorizationStrategy((IAuthorizationStrategy)new IAuthorizationStrategy.AllowAllAuthorizationStrategy() {
          public <T extends org.apache.wicket.request.component.IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
            if (IAuthenticatedWebPage.class.isAssignableFrom(componentClass)) {
              if (((BasicAuthenticationSession)Session.get()).isSignedIn()) {
                ((BasicAuthenticationSession)Session.get())
                  .getAttributeNames().stream().forEach(attr -> {
                    
                    });
                return true;
              } 
              throw new RestartResponseAtInterceptPageException(MicroAppSignInPage.class);
            } 
            return true;
          }
        });
  }
  
  public Class<? extends Page> getHomePage() {
    return (Class)MicroAppSignInPage.class;
  }
  
  private void initCheckers() {
    getComponentPostOnBeforeRenderListeners().add(new StatelessChecker());
  }
  
  protected IMapperContext newMapperContext() {
    return (IMapperContext)new CustomMapperContext();
  }
  
  protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
    return (Class)BasicAuthenticationSession.class;
  }
  
  protected Class<? extends WebPage> getSignInPageClass() {
    return (Class)MicroAppSignInPage.class;
  }
}
