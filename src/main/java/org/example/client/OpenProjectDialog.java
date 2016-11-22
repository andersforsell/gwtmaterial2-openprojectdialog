package org.example.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialInput;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 * Open Project dialog.
 * 
 * @author aforsell
 *
 */
public class OpenProjectDialog {

  /** The login callback interface. */
  interface OpenProjectDialogCallback {
    // The given File to be opened
    void confirmed(JavaScriptObject selectedFile);

    // Return <code>true</code> if the given File is accepted
    boolean accept(JavaScriptObject selectedFile);
  }

  private static Binder uiBinder = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, OpenProjectDialog> {
  }

  private OpenProjectDialogCallback callback;

  @UiField
  MaterialModal dialog;

  @UiField
  MaterialTextBox projectZipTextBox;

  @UiField
  MaterialInput projectZipInput;

  @UiField
  MaterialButton openButton;

  private JavaScriptObject selectedFile;

  /**
   * Constructor.
   * 
   * @param callback an IDialogCallback that will be called when 'ok' is pressed.
   */
  public OpenProjectDialog(OpenProjectDialogCallback callback) {
    uiBinder.createAndBindUi(this);
    dialog.addCloseHandler(event -> RootPanel.get().remove(dialog));
    
    this.callback = callback;
    openButton.setEnabled(false);
    projectZipInput.getElement().setAttribute("accept", ".zip");
  }

  @UiHandler("projectZipInput")
  void projectZipInputOnClick(ClickEvent event) {
    selectedFile = getResult(event.getNativeEvent());
    openButton.setEnabled(callback.accept(selectedFile));
    projectZipTextBox.setText(getResultName(event.getNativeEvent()));
  }

  @UiHandler("openButton")
  public void openButtonOnClick(ClickEvent event) {
    dialog.close();
    callback.confirmed(selectedFile);
  }

  @UiHandler("cancelButton")
  public void cancelButtonOnClick(ClickEvent event) {
    dialog.close();
  }

  /** Shows the dialog. */
  public void show() {
    RootPanel.get().add(dialog);
    dialog.open();
  }

  // Returns the first selected File object
  private native JavaScriptObject getResult(NativeEvent element) /*-{
    var file = element.target.files[0];
    return file;
  }-*/;

  // Returns the first selected File object
  private native String getResultName(NativeEvent element) /*-{
    var file = element.target.files[0].name;
    return file;
  }-*/;

}
