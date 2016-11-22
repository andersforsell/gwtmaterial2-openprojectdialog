package org.example.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import org.example.client.OpenProjectDialog.OpenProjectDialogCallback;

/**
 * This is the main view for the MINT client application.
 * 
 * @author aforsell
 *
 */
public class ClientView extends Composite {

  private static ClientViewUiBinder uiBinder = GWT.create(ClientViewUiBinder.class);

  interface ClientViewUiBinder extends UiBinder<Widget, ClientView> {
  }

  /**
   * Constructs the main view.
   */
  public ClientView() {

    initWidget(uiBinder.createAndBindUi(this));

  }

  @Override
  protected void onLoad() {}


  @UiHandler("openProjectLink")
  public void openProjectClick(ClickEvent event) {
    // The web platform uses a custom open project dialog
    OpenProjectDialog dialog = new OpenProjectDialog(new OpenProjectDialogCallback() {
      @Override
      public void confirmed(JavaScriptObject selectedFile) {
        
      }
      
      @Override
      public boolean accept(JavaScriptObject selectedFile) {
        return false;
      }
    });
    dialog.show();
  }
}
