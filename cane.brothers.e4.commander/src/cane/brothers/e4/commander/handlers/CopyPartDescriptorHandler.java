package cane.brothers.e4.commander.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.PartUtils;

/**
 * Open new tab directly after active part using PartDescriptor.
 * 
 * @see PartDescriptor
 * 
 * @author cane
 * 
 */
public class CopyPartDescriptorHandler {

	@Execute
	public void execute(EPartService partService) {
		System.out.println((this.getClass().getSimpleName() + " called"));

		// активная вкладка используется только для того,
		// чтобы определить id и имя для новой вкладки.
		MPart activePart = partService.getActivePart();
		// partService.addPartListener(listener);

		// create a new Part based on a PartDescriptor
		// in the application model
		// assume the ID is used for the PartDescriptor
		MPart newPart = partService.createPart(IdStorage.DYNAMIC_PART_DESCRIPTOR_ID);
		newPart = copyPart(newPart, activePart);

		// If multiple parts of this type are now allowed
		// in the application model,
		// then the provided part will be shown
		// and returned
		newPart = partService.showPart(newPart, PartState.ACTIVATE);
	}

	private MPart copyPart(MPart newPart, MPart part) {
		if (part != null) {

			newPart.setLabel(PartUtils.createPartLabel(part));
			newPart.setElementId(PartUtils.createElementId(part));

			/**
			 * Задавая ContributionURI мы, тем самым, определяем id view part,
			 * которая будет использоваться для создания новой вкладки.
			 */
			// задаем ContributionURI чтобы знать, какой создать новую закладку
			// другой вариант - определить для PartDescriptor id - свою вьюху
			// newPart.setContributionURI(part.getContributionURI());

			if(part.getParent() != null) {
				System.out.println("Parent id: " + part.getParent().getElementId());
			}
			
			
			// newPart.setCloseable(part.isCloseable());
			// Добавляем новую вкладку на туже сторону
			// 1.
			newPart.setParent(part.getParent());
			

			// 2.
			// MElementContainer<MUIElement> container = part.getParent();
			// container.getChildren().add(newPart);

			// 3. EPartService
			// MPartStack stack = (MPartStack)modelService.find(stack_id,
			// application);
			// stack.getChildren().add(part);
		}

		return newPart;
	}
}
