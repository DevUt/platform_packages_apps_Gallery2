/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.gallery3d.filtershow.filters;

public class FilterImageBorderRepresentation extends FilterRepresentation {
    private int mDrawableResource = 0;

    public FilterImageBorderRepresentation(int drawableResource) {
        super("ImageBorder");
        mDrawableResource = drawableResource;
        setFilterClass(ImageFilterBorder.class);
        setPriority(ImageFilter.TYPE_BORDER);
        // load the drawable at init as we are in a background thread
        // (see FilterShowActivity's LoadBordersTask)
        ImageFilterBorder filter = (ImageFilterBorder) FiltersManager.getManager().getFilter(getFilterClass());
        filter.getDrawable(getDrawableResource());
    }

    public String toString() {
        return "FilterBorder: " + getName();
    }

    @Override
    public FilterRepresentation clone() throws CloneNotSupportedException {
        FilterImageBorderRepresentation representation = (FilterImageBorderRepresentation) super.clone();
        representation.setName(getName());
        representation.setDrawableResource(getDrawableResource());
        return representation;
    }

    public void useParametersFrom(FilterRepresentation a) {
        if (a instanceof FilterImageBorderRepresentation) {
            FilterImageBorderRepresentation representation = (FilterImageBorderRepresentation) a;
            setName(representation.getName());
            setDrawableResource(representation.getDrawableResource());
        }
    }

    @Override
    public boolean equals(FilterRepresentation representation) {
        if (!super.equals(representation)) {
            return false;
        }
        if (representation instanceof FilterImageBorderRepresentation) {
            FilterImageBorderRepresentation border = (FilterImageBorderRepresentation) representation;
            if (border.mDrawableResource == mDrawableResource) {
                return true;
            }
        }
        return false;
    }

    public boolean allowsMultipleInstances() {
        return true;
    }

    public int getDrawableResource() {
        return mDrawableResource;
    }

    public void setDrawableResource(int drawableResource) {
        mDrawableResource = drawableResource;
    }
}