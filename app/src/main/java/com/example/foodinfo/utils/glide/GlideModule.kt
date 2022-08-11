package com.example.foodinfo.utils.glide

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.caverock.androidsvg.SVG
import com.example.foodinfo.repository.model.SVGModel
import com.example.foodinfo.utils.glide.svg.SVGDrawableTranscoder
import com.example.foodinfo.utils.glide.svg.SVGLocalDecoder
import com.example.foodinfo.utils.glide.svg.SVGModelLoaderFactory
import com.example.foodinfo.utils.glide.svg.SVGRemoteDecoder
import java.io.InputStream


@GlideModule
class GlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        registry
            .register(
                SVG::class.java,
                PictureDrawable::class.java,
                SVGDrawableTranscoder()
            )
            .append(
                InputStream::class.java,
                SVG::class.java,
                SVGRemoteDecoder()
            )
            .append(
                SVGModel::class.java,
                SVG::class.java,
                SVGLocalDecoder()
            )
            .prepend(
                SVGModel::class.java,
                SVGModel::class.java,
                SVGModelLoaderFactory()
            )
    }
}