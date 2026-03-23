package com.example.techup_miniproject_quangdv1.core.utils

enum class ImageMode(val requiredImageCount: Int) {
    COMBINE_IMAGES(2),
    HUGGING(2),
    POLAROID(2),
    MAKEUP(2),
    FACE_SWAP(2),
    INPAINTING(2),
    REMOVAL(2),
    COMBINE_IMAGES_SIMPLE(2),
    FITTING(2),

    FIGURE_MAKER(1),
    CUSTOM_FIGURE_MAKER(1),
    IMAGE_EDITING(1),
    IMG2IMG_TRT(1),
    RESTORE(1),
    ADAPTIVE_RESTORE(1),
    CLOTHES_EDITING(1),
    REMOVE_BACKGROUND(1),
    OUTPAINTING(1)
}