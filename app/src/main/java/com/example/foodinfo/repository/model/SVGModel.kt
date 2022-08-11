package com.example.foodinfo.repository.model


/**
 * Wrapper for SVG string that used in Glide.load() which also tells Glide to use proper decoders
 */
data class SVGModel(val content: String)