package net.hillsdon.testlink.actions;

import junit.framework.TestCase;
import net.hillsdon.testlink.model.IJavaSelection;
import net.hillsdon.testlink.model.ILinker;
import net.hillsdon.testlink.model.Linkage;
import net.hillsdon.testlink.preferences.ITestlinkPreferences;
import net.hillsdon.testlink.preferences.PreferencesMissingException;
import net.hillsdon.testlink.ui.IUserInteraction;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.ILog;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;

public class TestCodeTestToggleAction extends TestCase {

  private ILinker _locator;
  private IJavaSelection _selection;
  private IUserInteraction _ui;
  private ILog _log;
  private ITestlinkPreferences _preferences;
  
  private CodeTestToggleAction _action;
  private IType _type;

  @Override
  protected void setUp() throws Exception {
    _preferences = createMock(ITestlinkPreferences.class);
    _locator = createMock(ILinker.class);
    _selection = createMock(IJavaSelection.class);
    _ui = createMock(IUserInteraction.class);
    _log = createMock(ILog.class);
    _type = createMock(IType.class);
    _action = new CodeTestToggleAction(_preferences, _locator, _selection, _ui, _log);
  }
  
  private void verify() {
    EasyMock.verify(_locator, _selection, _ui, _log, _preferences);
  }

  private void replay() {
    EasyMock.replay(_locator, _selection, _ui, _log, _preferences);
  }

  public void testNullSelectionDoesNothing() throws Exception {
    expect(_selection.getSelectedType()).andReturn(null);
    replay();
    _action.run(null);
    verify();
  }
  
  public void testTypeSelectedHaveOppositeFileResourceOpens() throws Exception {
    IFile resource = createMock(IFile.class);
    expectTypeSelected(resource);
    _ui.openEditor(resource);
    replay();
    _action.run(null);
    verify();
  }
  
  public void testTypeSelectedHaveOppositeResourceButNotFileIgnores() throws Exception {
    expectTypeSelected(createMock(IResource.class));
    replay();
    _action.run(null);
    verify();
  }

  public void testTypeSelectedNoOppositeResourceCreates() throws Exception {
    Linkage linkage = expectTypeSelected(null);
    linkage.createOther(_ui, _preferences, _type);
    replay();
    _action.run(null);
    verify();
  }
  
  public void testShowsDialogIfPreferencesIncomplete() throws Exception {
    Linkage linkage = createMock(Linkage.class);
    expect(_selection.getSelectedType()).andReturn(_type);
    expect(_locator.getLinkageFor(_type)).andReturn(linkage);
    expect(_locator.find(_type, linkage)).andThrow(new PreferencesMissingException());
    _ui.showPreferencesMissingDialog();
    replay();
    _action.run(null);
    verify();
  }
  
  private Linkage expectTypeSelected(final IResource foundResource) throws Exception {
    Linkage linkage = createMock(Linkage.class);
    expect(_selection.getSelectedType()).andReturn(_type);
    expect(_locator.getLinkageFor(_type)).andReturn(linkage);
    expect(_locator.find(_type, linkage)).andReturn(foundResource);
    return linkage;
  }

  public void testDisabledIfSelectionNull() throws Exception {
    expect(_selection.getSelectedType()).andReturn(null);
    verifyEnablementSet(false);
  }

  public void testEnabledIfSelectionNonNull() throws Exception {
    expect(_selection.getSelectedType()).andReturn(_type);
    verifyEnablementSet(false);
  }
  
  public void testDisabledIfSelectionThrows() throws Exception {
    expect(_selection.getSelectedType()).andThrow(new JavaModelException(null, -1));
    verifyEnablementSet(false);
  }
  
  private void verifyEnablementSet(boolean expected) {
    IAction action = EasyMock.createMock(IAction.class);
    action.setEnabled(expected);
    ISelection selection = createMock(ISelection.class);
    _selection.selectionChanged(selection);
    EasyMock.replay(action);
    _action.selectionChanged(action, selection);
    EasyMock.verify(action);
  }
  
}
