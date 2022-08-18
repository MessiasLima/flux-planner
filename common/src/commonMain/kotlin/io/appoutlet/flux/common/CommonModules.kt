package io.appoutlet.flux.common

import io.appoutlet.flux.common.core.coreModules
import io.appoutlet.flux.common.domain.domainModules
import io.appoutlet.flux.common.feature.commonFeatureModules
import kotlinx.coroutines.FlowPreview

@FlowPreview
val commonModules = arrayOf(
    * coreModules,
    * commonFeatureModules,
    * domainModules,
)
