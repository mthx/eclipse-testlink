package net.hillsdon.testlink.model.impl;

import net.hillsdon.testlink.model.IJavaSelection;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.actions.SelectionConverter;
import org.eclipse.jdt.internal.ui.javaeditor.JavaEditor;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

@SuppressWarnings("restriction")
public class JavaSelection implements IJavaSelection {

  /**
   * May be null.
   */
  private ISelection _workbenchSelection;

  public void selectionChanged(final ISelection selection) {
    _workbenchSelection = selection;
  }

  /**
   * Using the current workbench selection determine the
   * Java model component that is selected, if any.
   *
   * @return The model component, or null.
   * @throws JavaModelException If an error occurs reading the java model.
   */
  private IJavaElement getSelectedJavaElement() throws JavaModelException {
    // We have an editor
    if (_workbenchSelection instanceof ITextSelection) {
      // Would be nice if there was a better way to do this.  Interestingly
      // the text selections from JavaEditors aren't JavaTextSelections.
      // Seems better to re-use this code rather than copy and paste.
      IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      if (editor instanceof JavaEditor) {
        return SelectionConverter.getElementAtOffset((JavaEditor) editor);
      }
    }
    // Selection in e.g. package explorer
    else if (_workbenchSelection instanceof IStructuredSelection) {
      Object element = ((IStructuredSelection) _workbenchSelection).getFirstElement();
      if (element instanceof IJavaElement) {
        return (IJavaElement) element;
      }
    }
    return null;
  }

  /**
   * Find the type best associated with the java element.
   *
   * @param javaElement A java element.
   * @return A type, or null if we can't figure one out.
   * @throws JavaModelException If we fail to read from the java model.
   */
  public IType getSelectedType() throws JavaModelException {
    final IJavaElement javaElement = getSelectedJavaElement();
    IType type = null;
    if (javaElement != null) {
      type = getOutermostType(javaElement);
      if (type == null) {
        ICompilationUnit cu = (ICompilationUnit) javaElement.getAncestor(IJavaElement.COMPILATION_UNIT);
        if (cu != null) {
          IType[] types = cu.getTypes();
          if (types.length > 0) {
            type = types[0];
          }
        }
      }
    }
    return type;
  }

  /**
   * We bias towards the outermost type for two reasons:
   *   1) It won't be anonymous
   *   2) If you're going to test it it probably deserves its own file and test class...
   *   
   * @param javaElement Any java element.
   * @return
   */
  private IType getOutermostType(final IJavaElement javaElement) {
    IJavaElement outermost = javaElement;
    while (true) {
      IType better = (IType) outermost.getParent().getAncestor(IJavaElement.TYPE);
      if (better == null || better.equals(outermost)) {
        break;
      }
      outermost = better;
    }
    return outermost instanceof IType ? (IType) outermost : null;
  }

}
