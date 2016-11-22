package org.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Module implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    
    GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
      @Override
      public void onUncaughtException(Throwable error) {
        Window.alert("uncaught: " + error.getLocalizedMessage());
        Window.alert(error.getMessage());
      }
    });


    RootLayoutPanel.get().add(new ClientView());
    
  }
}
