package cane.brothers.e4.commander.handlers.internal;

import java.nio.file.Path;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.api.IResolveSelection;
import cane.brothers.e4.commander.event.PartEvents;
import cane.brothers.e4.commander.preferences.PreferenceConstants;
import cane.brothers.e4.commander.service.api.IPartService;
import cane.brothers.e4.commander.service.api.ITabService;

public class RefreshPathPartHandler implements IResolveSelection {

    private static final Logger log = LoggerFactory.getLogger(RefreshPathPartHandler.class);

    @Inject
    @Named(IServiceConstants.ACTIVE_PART)
    private MPart activePart;

    @Inject
    ITabService tabService;

    @Inject
    private IPartService partService;

    @Inject
    @Preference(value = PreferenceConstants.PB_STAY_ACTIVE_TAB, nodePath = IdStorage.PREF_PLUGIN_ID)
    private boolean stayActiveTab;

    @Inject
    @Optional
    @Execute
    public void execute(@UIEventTopic(PartEvents.TOPIC_PART_PATH_REFRESH) final Path newPath) {
        if (log.isDebugEnabled()) {
            log.debug(this.getClass().getSimpleName() + " called"); //$NON-NLS-1$
        }

        // resolve selection
        resolveSelections(activePart);
    }

    @Override
    public void resolveSelections(MPart activePart) {
        tabService.clearSelection(partService.getVisiblePart(activePart, !stayActiveTab));
        tabService.setSelection(activePart);
    }

}
